package com.baltazar.myreminder.remainderList

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baltazar.myreminder.R
import com.baltazar.myreminder.database.RemainderEntity

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class RemainderListActivity: AppCompatActivity(), RemainderListContract.View {

    private var mRecyclerView: RecyclerView? = null
    private var mTextEmptyMessage: TextView? = null
    private var mToolbar: Toolbar? = null

    private var mPresenter: RemainderListPresenter? = null

    private lateinit var mRecyclerViewAdapter: RemainderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remainder_list)
        mPresenter = RemainderListPresenter(this)
        setupWidget()
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.onAttach(this)
        mPresenter?.loadRemainders()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showRemainders(remainders: List<RemainderEntity>) {
        mTextEmptyMessage?.visibility = View.GONE
        mRecyclerView?.visibility = View.VISIBLE
        mRecyclerViewAdapter.updateRemaindersList(remainders)
    }

    override fun showRemaindersEmpty() {
        mRecyclerView?.visibility = View.GONE
        mTextEmptyMessage?.visibility = View.VISIBLE
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupWidget() {
        mRecyclerView = findViewById(R.id.recycler_view)
        mTextEmptyMessage = findViewById(R.id.text_empty_list)
        mToolbar = findViewById(R.id.toolbar)
        mToolbar?.setNavigationIcon(R.drawable.ic_arrow_back)

        mRecyclerViewAdapter = RemainderListAdapter(arrayListOf())

        mToolbar?.let { toolbar ->
            toolbar.setTitleTextColor(Color.WHITE)
            setSupportActionBar(toolbar)

            supportActionBar?.apply {
                title = resources.getString(R.string.title_remainders)
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(false)
            }
        }

        mRecyclerView?.apply {
            layoutManager = LinearLayoutManager(this@RemainderListActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = mRecyclerViewAdapter
        }
    }
}