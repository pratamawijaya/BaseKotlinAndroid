package com.pratamawijaya.basekotlin.presentation.base

import com.github.ajalt.timberkt.d
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by pratama
 * Date : Jul - 7/6/17
 */
open class BasePresenter<T : BaseView> : Presenter<T> {

    var mView: T? = null
    lateinit var compositeDisposable: CompositeDisposable

    override fun attachView(mView: T) {
        d { "base presenter attach" }
        this.mView = mView
        compositeDisposable = CompositeDisposable()
    }

    override fun detachView() {
        d { "base presenter detach" }
        mView = null
        compositeDisposable.dispose()
    }

    val isViewAttached: Boolean
        get() = mView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before"
            + " requesting data to the Presenter")
}