package com.example.dotaheroes.api.requests

import com.example.dotaheroes.api.models.Hero
import retrofit2.Response
import retrofit2.http.GET

interface HeroApiClient {
    @GET("/api/heroStats")
    suspend fun getAllHeroes(): Response<List<Hero>>
}