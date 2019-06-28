package com.baltazar.myreminder.remainderDetail

import android.content.Context
import com.baltazar.myreminder.database.AppDataBase
import com.baltazar.myreminder.database.RemainderEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class RemainderDetailPresenter(context: Context): RemainderDetailContract.Presenter {

    private var mView: RemainderDetailContract.View? = null

    private val mComposite = CompositeDisposable()

    private val mDataBase = AppDataBase.getAppDataBase(context)

    override fun onAttach(view: RemainderDetailContract.View) {
        mView = view
    }

    override fun onDestroy() {
        mComposite.clear()
    }

    override fun loadRemainder(remainderId: Int) {
        mDataBase?.remainderDao()?.let { dao ->
            val disposable = dao.getRemainderById(remainderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleRemainder, this::handleRemainderError)
            mComposite.add(disposable)
        }
    }

    override fun updateRemainder(title: String, description: String) {
        mDataBase?.remainderDao()?.let { dao ->
            val remainder = RemainderEntity(title = title, description = description, scheduleDate = Date())
            val disposable = dao.updateRemainder(remainder)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { mView?.remainderUpdated() }
                .doOnError { handleRemainderError(it) }
                .subscribe()
            mComposite.add(disposable)
        }
    }

    override fun deleteRemainder(remainderId: Int) {
        mDataBase?.remainderDao()?.let { dao ->
            val disposable = dao.deleteRemainderById(remainderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { mView?.remainderDeleted() }
                .doOnError { handleRemainderError(it) }
                .subscribe()
            mComposite.add(disposable)
        }
    }

    private fun handleRemainder(remainder: RemainderEntity) {
        mView?.showRemainder(remainder)
    }

    private fun handleRemainderError(error: Throwable) {
        mView?.showMessage(error.localizedMessage)
    }
}