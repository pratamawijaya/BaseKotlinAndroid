package com.pratamawijaya.basekotlin.presentation.base


/**
 * Created by pratama
 * Date : Jul - 7/6/17
 */
interface Presenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}
