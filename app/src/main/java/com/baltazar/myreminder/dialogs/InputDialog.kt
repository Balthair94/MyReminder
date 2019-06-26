package com.baltazar.myreminder.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.baltazar.myreminder.R

/**
 * @author Baltazar Rodriguez
 * @since v0.0.4
 */
class InputDialog: DialogFragment() {

    private var mButtonSave: Button? = null
    private var mButtonCancel: Button? = null
    private var mEditText: EditText? = null

    private var mListener: InputDialogListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWidgets(view)
    }

    fun setListener(listener: InputDialogListener) { mListener = listener }

    private fun setupWidgets(view: View) {
        val userName = arguments?.getString(USER_NAME, "") ?: ""
        mButtonCancel = view.findViewById(R.id.button_cancel)
        mButtonSave = view.findViewById(R.id.button_save)
        mEditText = view.findViewById(R.id.edit_user_name)

        mEditText?.setText(userName)

        mButtonCancel?.setOnClickListener { dismiss() }

        mButtonSave?.setOnClickListener {
            mListener?.onSaveUserName(mEditText?.text.toString())
            dismiss()
        }
    }

    companion object {
        private const val USER_NAME = "user_name"

        fun newInstance(userName: String): InputDialog {
            val dialog = InputDialog()
            val bundle = Bundle()
            bundle.putString(USER_NAME, userName)
            dialog.arguments = bundle
            return dialog
        }
    }
}