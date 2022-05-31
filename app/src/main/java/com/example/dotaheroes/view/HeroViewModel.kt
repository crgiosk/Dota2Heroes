package com.example.dotaheroes.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dotaheroes.api.RetrofitHelper
import com.example.dotaheroes.api.models.HeroApiResponse
import com.example.dotaheroes.api.models.HeroGamesPlayed
import com.example.dotaheroes.api.requests.HeroApiClient
import com.example.dotaheroes.core.Extensions.ifNull
import kotlinx.coroutines.launch

class HeroViewModel : ViewModel() {

    var heroApiResponse: HeroApiResponse? = null

    fun getHeroMatches(idHero: Int, response: (List<HeroGamesPlayed>) -> Unit){
        val retrofit = RetrofitHelper.getRetrofitInstance()
        viewModelScope.launch {
            val result = retrofit.create(HeroApiClient::class.java).getHeroMatches(idHero)
            response(result.body() ?:  listOf() )
        }

    }

    fun getHeroes(response: (List<HeroApiResponse>) -> Unit) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        viewModelScope.launch {
            val result = retrofit.create(HeroApiClient::class.java).getAllHeroes()
            response(result.body() ?: listOf())
        }
    }
}