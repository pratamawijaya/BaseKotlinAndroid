package com.pratamawijaya.basekotlin.presentation.home

import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.ajalt.timberkt.d
import com.pratamawijaya.basekotlin.R
import com.pratamawijaya.basekotlin.di.component.ActivityComponent
import com.pratamawijaya.basekotlin.entity.Hero
import com.pratamawijaya.basekotlin.presentation.base.BaseInjectedActivity
import javax.inject.Inject

class MainActivity : BaseInjectedActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    @BindView(R.id.txtHeroes)
    lateinit var tvHeroes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        presenter.attachView(this)
        presenter.getHeroes()

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

    override fun displayHeroes(heroes: List<Hero>) {
        heroes.map {
            d { "heroes ${it.localName} ${it.id}" }
            tvHeroes.append("${it.localName} ${it.id} \n")
        }
    }
}
