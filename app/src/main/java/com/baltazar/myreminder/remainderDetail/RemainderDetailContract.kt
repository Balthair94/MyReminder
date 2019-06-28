package com.baltazar.myreminder.remainderDetail

import com.baltazar.myreminder.database.RemainderEntity

/**
 * @author Baltazar Rodriguez
 * @since v
 */
interface RemainderDetailContract {

    interface View {
        fun showRemainder(remainder: RemainderEntity)

        fun showMessage(message: String)

        fun remainderUpdated()

        fun remainderDeleted()
    }

    interface Presenter {
        fun onAttach(view: View)

        fun onDestroy()

        fun loadRemainder(remainderId: Int)

        fun updateRemainder(title: String, description: String)

        fun deleteRemainder(remainderId: Int)
    }
}