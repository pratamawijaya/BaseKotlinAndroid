package com.pratamawijaya.basekotlin.data.model

import com.google.gson.annotations.SerializedName

data class HeroModel(
        @field:SerializedName("primary_attr")
        val primaryAttr: String = "",

        @field:SerializedName("attack_type")
        val attackType: String = "",

        @field:SerializedName("roles")
        val roles: List<String>? = null,

        @field:SerializedName("legs")
        val legs: Int = 0,

        @field:SerializedName("name")
        val name: String = "",

        @field:SerializedName("id")
        val id: Int = 0,

        @field:SerializedName("localized_name")
        val localizedName: String = ""
)