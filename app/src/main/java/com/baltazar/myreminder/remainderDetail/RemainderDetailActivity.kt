package com.baltazar.myreminder.remainderDetail

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.baltazar.myreminder.R
import com.baltazar.myreminder.database.RemainderEntity

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class RemainderDetailActivity: AppCompatActivity(), RemainderDetailContract.View {

    private var mTextRemainderId: TextView? = null
    private var mTextRemainderDate: TextView? = null
    private var mEditRemainderTitle: EditText? = null
    private var mEditRemainderDescription: EditText? = null
    private var mButtonUpdateRemainder: Button? = null
    private var mButtonDeleteRemainder: Button? = null
    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remainder_detail)

        setupWidget()
    }

    override fun showRemainder(remainder: RemainderEntity) {

    }

    override fun showMessage(message: String) {

    }

    override fun remainderUpdated() {

    }

    override fun remainderDeleted() {

    }

    private fun setupWidget() {
        mTextRemainderId = findViewById(R.id.text_remainder_id)
        mTextRemainderDate = findViewById(R.id.text_remainder_date)
        mEditRemainderTitle = findViewById(R.id.edit_remainder_title)
        mEditRemainderDescription = findViewById(R.id.edit_remainder_description)
        mButtonUpdateRemainder = findViewById(R.id.button_update)
        mButtonDeleteRemainder = findViewById(R.id.button_delete)
        mToolbar = findViewById(R.id.toolbar)
        mToolbar?.setNavigationIcon(R.drawable.ic_arrow_back)

        mToolbar?.let { toolbar ->
            toolbar.setTitleTextColor(Color.WHITE)
            setSupportActionBar(toolbar)

            supportActionBar?.apply {
                title = resources.getString(R.string.title_remainder_detail)
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(false)
            }
        }
    }
}