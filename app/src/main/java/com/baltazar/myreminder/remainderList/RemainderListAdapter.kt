package com.baltazar.myreminder.remainderList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.baltazar.myreminder.R
import com.baltazar.myreminder.database.RemainderEntity

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class RemainderListAdapter (private val mRemainders: MutableList<RemainderEntity>): RecyclerView.Adapter<RemainderListAdapter.RemainderViewHolder>() {

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemainderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_remainder, parent, false)
        mContext = parent.context
        return RemainderViewHolder(view)
    }

    override fun getItemCount(): Int = mRemainders.size

    override fun onBindViewHolder(holder: RemainderViewHolder, position: Int) {
        holder.mTextRemainderTitle.text = mRemainders[position].title
        holder.mTextRemainderDate.text = mRemainders[position].scheduleDate.toString()
        holder.mLayoutContainer.setOnClickListener {
            mContext?.let {
                Toast.makeText(it, "Item Pressed: ${mRemainders[position].id}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateRemaindersList(remainders: List<RemainderEntity>) {
        mRemainders.clear()
        mRemainders.addAll(remainders)
        notifyDataSetChanged()
    }

    class RemainderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val mTextRemainderTitle: TextView = view.findViewById(R.id.text_remainder_title)
        val mTextRemainderDate: TextView = view.findViewById(R.id.text_remainder_date)
        val mLayoutContainer: LinearLayout = view.findViewById(R.id.layout_container)
    }
}