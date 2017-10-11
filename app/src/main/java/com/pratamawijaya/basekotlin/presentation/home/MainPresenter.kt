package com.pratamawijaya.basekotlin.presentation.home

import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.pratamawijaya.basekotlin.data.PreferencesManager
import com.pratamawijaya.basekotlin.data.repository.HeroRepository
import com.pratamawijaya.basekotlin.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by pratama on 8/7/17.
 */
class MainPresenter @Inject constructor(private val repo: HeroRepository,
                                        private val preferencesManager: PreferencesManager) : BasePresenter<MainView>() {

    private var compositeSub = CompositeDisposable()

    /**
     * get dota 2 heroes list
     */
    fun getHeroes() {

        preferencesManager.setUserLogin(true)

        compositeSub.add(repo.getHeroes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    d { "hasilnya adalah" }
                    mView?.displayHeroes(result)
                }, { error ->
                    e { "error ${error.localizedMessage}" }
                }))
    }
}