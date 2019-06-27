package com.baltazar.myreminder.addRemainder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.baltazar.myreminder.R
import java.util.Calendar

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class AddRemainderActivity: AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private var mEditRemainderTitle: EditText? = null
    private var mEditRemainderDescription: EditText? = null
    private var mTextDay: TextView? = null
    private var mTextHour: TextView? = null

    private var mButtonSave: Button? = null
    private var mButtonCancel: Button? = null

    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_remainder)

        setupWidget()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
    }

    private fun setupWidget() {
        mEditRemainderTitle = findViewById(R.id.edit_remainder_title)
        mEditRemainderDescription = findViewById(R.id.edit_remainder_description)
        mTextDay = findViewById(R.id.text_remainder_day)
        mTextHour = findViewById(R.id.text_remainder_hour)

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

        mTextDay?.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dialog = DatePickerDialog(
                this,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
            dialog.show()
        }

        mTextHour?.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePicker = TimePickerDialog(
                this,
                this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true)
            timePicker.show()
        }
    }
}