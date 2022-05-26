package com.example.dotaheroes.api.models

import com.google.gson.annotations.SerializedName

data class Hero(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("localized_name")
    val localizedName: String,
    @SerializedName("primary_attr")
    val primaryAttr: String,
    @SerializedName("attack_type")
    val attackType: String,
    @SerializedName("img")
    val image: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("roles")
    val roles: List<String> = mutableListOf()
) {
    companion object {
        fun String.transformAttribute() = when (this) {
                ATTR_STRENGTH -> STRENGTH
                ATTR_AGILITY -> AGILITY
                ATTR_INTELLIGENCE -> INTELLIGENCE
                else -> String()
            }


        private const val STRENGTH = "strength"
        private const val AGILITY = "agility"
        private const val INTELLIGENCE = "intelligence"
        private const val ATTR_STRENGTH = "str"
        private const val ATTR_AGILITY = "agi"
        private const val ATTR_INTELLIGENCE = "int"
    }

}