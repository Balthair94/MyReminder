package com.baltazar.myreminder.addRemainder

import io.reactivex.disposables.CompositeDisposable

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class AddRemainderPresenter: AddRemainderContract.Presenter {

    private var mView: AddRemainderContract.View? = null
    private val mComposite = CompositeDisposable()

    override fun onAttach(view: AddRemainderContract.View) {
        mView = view
    }

    override fun onAddRemainder(title: String, description: String) {
        if (title.isNotBlank() && description.isNotBlank()) {
            mView?.onRemainderAdded()
        } else {
            mView?.onShowError("Faltan datos")
        }
    }

    override fun onDestroy() {
        mComposite.clear()
    }
}