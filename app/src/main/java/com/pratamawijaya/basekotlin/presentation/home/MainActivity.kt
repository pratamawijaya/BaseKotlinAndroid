package com.pratamawijaya.basekotlin.presentation.home

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.github.ajalt.timberkt.d
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.pratamawijaya.basekotlin.BR
import com.pratamawijaya.basekotlin.R
import com.pratamawijaya.basekotlin.data.PreferencesManager
import com.pratamawijaya.basekotlin.databinding.ItemHeroesBinding
import com.pratamawijaya.basekotlin.di.component.ActivityComponent
import com.pratamawijaya.basekotlin.entity.Hero
import com.pratamawijaya.basekotlin.presentation.base.BaseInjectedActivity
import com.pratamawijaya.basekotlin.presentation.utils.toGone
import com.pratamawijaya.basekotlin.presentation.utils.toVisible
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : BaseInjectedActivity(), MainView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: MainPresenter
    @Inject
    lateinit var prefManager: PreferencesManager

    @BindView(R.id.rvMain)
    lateinit var rvMain: RecyclerView
    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar
    @BindView(R.id.content)
    lateinit var content: SwipeRefreshLayout
    @BindView(R.id.loader)
    lateinit var loadingView: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        setupRecyclerView()

        presenter.attachView(this)
        presenter.getHeroes()

        prefManager.saveString(PreferencesManager.PREF_USERNAME, "hello")
        content.setOnRefreshListener(this)
    }

    private fun setupRecyclerView() {
        rvMain.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun injectModule(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun showLoading() {
        content.toGone()
        loadingView.toVisible()
    }

    override fun hideLoading() {
        content.toVisible()
        loadingView.toGone()
    }

    override fun displayHeroes(heroes: List<Hero>) {

        LastAdapter(heroes, BR.hero)
                .map<Hero>(Type<ItemHeroesBinding>(R.layout.item_heroes).onClick {
                    toast("${it.binding.hero.localName} selected")
                })
                .into(rvMain)

        heroes.map {
            d { "heroes ${it.localName} ${it.id}" }
            d { "heroes image ${it.heroesImage}" }
            d { "heroes role ${it.roles.toString()}" }
        }
    }

    override fun onRefresh() {
        content.isRefreshing = false
        presenter.getHeroes()
    }
}
