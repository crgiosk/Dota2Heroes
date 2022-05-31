package com.example.dotaheroes.api.requests

import com.example.dotaheroes.api.models.HeroApiResponse
import com.example.dotaheroes.api.models.HeroGamesPlayed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApiClient {
    @GET("/api/heroStats")
    suspend fun getAllHeroes(): Response<List<HeroApiResponse>>
    @GET("/api/heroes/{idHero}/matchups")
    suspend fun getHeroMatches(@Path("idHero") idHero: Int): Response<List<HeroGamesPlayed>>
}