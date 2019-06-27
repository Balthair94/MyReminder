package com.baltazar.myreminder.addRemainder

/**
 * @author Baltazar Rodriguez
 * @since v
 */
interface AddRemainderContract {

    interface View {
        fun onRemainderAdded()

        fun onShowError(error: String)
    }

    interface Presenter {
        fun onAttach(view: View)

        fun onDestroy()

        fun onAddRemainder(title: String, description: String)
    }
}