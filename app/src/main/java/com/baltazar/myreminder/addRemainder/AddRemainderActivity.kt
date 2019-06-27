package com.baltazar.myreminder.addRemainder

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.baltazar.myreminder.R

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class AddRemainderActivity: AppCompatActivity(), AddRemainderContract.View {

    private var mEditRemainderTitle: EditText? = null
    private var mEditRemainderDescription: EditText? = null

    private var mButtonSave: Button? = null
    private var mButtonCancel: Button? = null

    private var mToolbar: Toolbar? = null

    private var mPresenter: AddRemainderPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_remainder)

        setupWidget()

        mPresenter = AddRemainderPresenter()
        mPresenter?.onAttach(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRemainderAdded() {
        Toast.makeText(this, "Recordatorio agregado", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onShowError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun setupWidget() {
        mEditRemainderTitle = findViewById(R.id.edit_remainder_title)
        mEditRemainderDescription = findViewById(R.id.edit_remainder_description)

        mButtonSave = findViewById(R.id.button_save)
        mButtonCancel = findViewById(R.id.button_cancel)

        mToolbar = findViewById(R.id.toolbar)
        mToolbar?.setNavigationIcon(R.drawable.ic_arrow_back)

        mToolbar?.let { toolbar ->
            toolbar.setTitleTextColor(Color.WHITE)
            setSupportActionBar(toolbar)

            supportActionBar?.apply {
                title = resources.getString(R.string.app_name)
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(false)
            }
        }

        mButtonCancel?.setOnClickListener { finish() }

        mButtonSave?.setOnClickListener {
            mPresenter?.onAddRemainder(mEditRemainderTitle?.text.toString(), mEditRemainderDescription?.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }
}