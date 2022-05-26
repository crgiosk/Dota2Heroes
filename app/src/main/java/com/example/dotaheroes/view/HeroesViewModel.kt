package com.example.dotaheroes.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dotaheroes.api.RetrofitHelper
import com.example.dotaheroes.api.models.Hero
import com.example.dotaheroes.api.requests.HeroApiClient
import kotlinx.coroutines.launch

class HeroesViewModel : ViewModel() {
    fun getHeroes(response: (List<Hero>) -> Unit) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        viewModelScope.launch {
            val result = retrofit.create(HeroApiClient::class.java).getAllHeroes()
            response(result.body() ?: listOf())
        }
    }
}