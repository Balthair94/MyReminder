package com.baltazar.myreminder

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private var mButtonAddRemainder: Button? = null
    private var mButtonOpenRemainders: Button? = null
    private var mTextGreetings: TextView? = null
    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWidget()
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
    }
}
