package com.baltazar.myreminder

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.baltazar.myreminder.utils.SharePreferencesCoordinator
import android.content.DialogInterface
import android.content.Intent
import android.widget.Toast
import com.baltazar.myreminder.addRemainder.AddRemainderActivity
import com.baltazar.myreminder.database.AppDataBase
import com.baltazar.myreminder.dialogs.InputDialog
import com.baltazar.myreminder.dialogs.InputDialogListener
import com.baltazar.myreminder.remainderList.RemainderListActivity


class MainActivity : AppCompatActivity(), InputDialogListener {

    private var mButtonAddRemainder: Button? = null
    private var mButtonOpenRemainders: Button? = null
    private var mTextGreetings: TextView? = null
    private var mToolbar: Toolbar? = null

    private var mSharePreferences: SharePreferencesCoordinator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWidget()
    }

    override fun onResume() {
        super.onResume()
        mTextGreetings?.text =
            String.format(
                resources.getString(R.string.format_hello),
                mSharePreferences?.getUserName() ?: "Null"
            )
    }

    override fun onSaveUserName(newUserName: String) {
        if(newUserName.isBlank()) {
            Toast.makeText(this, resources.getString(R.string.error_no_name), Toast.LENGTH_SHORT).show()
        } else {
            mSharePreferences?.saveUserName(newUserName)
            mTextGreetings?.text = String.format(resources.getString(R.string.format_hello), newUserName)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppDataBase.destroyDataBaseInstance()
    }

    private fun setupWidget() {
        mButtonAddRemainder = findViewById(R.id.button_add_remainder)
        mButtonOpenRemainders = findViewById(R.id.button_open_remainders)
        mTextGreetings = findViewById(R.id.text_greetings)
        mToolbar = findViewById(R.id.toolbar)

        mToolbar?.let {
            it.setTitleTextColor(Color.WHITE)
            setSupportActionBar(it)
            supportActionBar?.setTitle(resources.getString(R.string.app_name))
        }

        mSharePreferences = SharePreferencesCoordinator(this)

        mTextGreetings?.setOnClickListener {
            val dialog = InputDialog.newInstance(mSharePreferences?.getUserName() ?: "Null")
            dialog.setListener(this)
            dialog.show(supportFragmentManager, "input")
        }

        mButtonAddRemainder?.setOnClickListener {
            val intent = Intent(this, AddRemainderActivity::class.java)
            startActivity(intent)
        }

        mButtonOpenRemainders?.setOnClickListener {
            val intent = Intent(this, RemainderListActivity::class.java)
            startActivity(intent)
        }
    }
}
