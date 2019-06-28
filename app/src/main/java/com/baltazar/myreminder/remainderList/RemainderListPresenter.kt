package com.baltazar.myreminder.remainderList

import android.content.Context
import com.baltazar.myreminder.database.AppDataBase
import com.baltazar.myreminder.database.RemainderEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class RemainderListPresenter(context: Context): RemainderListContract.Presenter {

    private var mView: RemainderListContract.View? = null
    private val mComposite = CompositeDisposable()

    private val mDataBase = AppDataBase.getAppDataBase(context)

    override fun onAttach(view: RemainderListContract.View) {
        mView = view
    }

    override fun loadRemainders() {
        mDataBase?.remainderDao()?.let { dao ->
            val disposable = dao.getRemainders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleRemainders, this::handleRemaindersError)
            mComposite.add(disposable)
        }
    }

    private fun handleRemainders(remainders: List<RemainderEntity>) {
        if (remainders.isEmpty()) {
            mView?.showRemaindersEmpty()
        } else {
            mView?.showRemainders(remainders)
        }
    }

    private fun handleRemaindersError(error: Throwable) {
        mView?.showMessage(error.localizedMessage)
        mView?.showRemaindersEmpty()
    }

    override fun onDestroy() {
        mComposite.clear()
    }
}