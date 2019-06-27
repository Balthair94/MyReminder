package com.baltazar.myreminder.addRemainder

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
class AddRemainderPresenter(mContext: Context): AddRemainderContract.Presenter {

    private var mView: AddRemainderContract.View? = null
    private val mComposite = CompositeDisposable()

    private val mDataBase = AppDataBase.getAppDataBase(mContext)

    override fun onAttach(view: AddRemainderContract.View) {
        mView = view
    }

    override fun onAddRemainder(title: String, description: String) {
        if (title.isNotBlank() && description.isNotBlank()) {
            val remainderDao = mDataBase?.remainderDao()
            remainderDao?.let { dao ->
                val disposable = dao.insertRemainder(RemainderEntity(title = title, description = description, scheduleDate = Date()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete { mView?.onRemainderAdded() }
                    .doOnError { mView?.onShowError(it.localizedMessage) }
                    .subscribe()
                mComposite.add(disposable)
            }
        } else {
            mView?.onShowError("Faltan datos")
        }
    }

    override fun onDestroy() {
        mComposite.clear()
    }
}