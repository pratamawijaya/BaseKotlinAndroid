package com.pratamawijaya.basekotlin.data.model

import com.google.gson.annotations.SerializedName

data class HeroModel(
        @field:SerializedName("primary_attr")
        val primaryAttr: String? = null,

        @field:SerializedName("attack_type")
        val attackType: String? = null,

        @field:SerializedName("roles")
        val roles: List<String>? = null,

        @field:SerializedName("legs")
        val legs: Int? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("localized_name")
        val localizedName: String? = null
)