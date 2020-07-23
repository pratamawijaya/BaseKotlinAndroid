package com.pratama.baseandroid.routing

import com.github.florent37.navigator.Param
import com.github.florent37.navigator.Route
import com.github.florent37.navigator.RouteWithParams

object Routes {
    object Splash : Route("/")

    object HomePage : Route("/home")

    object DetailPage : RouteWithParams<DetailPage.Paramater>("/detail/{id}") {
        data class Paramater(val id: Int) : Param()
    }
}