package com.example.dotaheroes.api.models

import com.google.gson.annotations.SerializedName

data class HeroGamesPlayed(
    @SerializedName("hero_id")
    val heroID: Long,

    @SerializedName("games_played")
    val gamesPlayed: Long,

    @SerializedName("wins")
    val wins: Long
)