package com.baltazar.myreminder.remainderList

import com.baltazar.myreminder.database.RemainderEntity

/**
 * @author Baltazar Rodriguez
 * @since v
 */
interface RemainderListContract {

    interface View {
        fun showRemainders(remainders: List<RemainderEntity>)

        fun showRemaindersEmpty()

        fun showMessage(message: String)
    }

    interface Presenter {
        fun onAttach(view: View)

        fun onDestroy()

        fun loadRemainders()
    }
}