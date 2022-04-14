package com.kurilov.worktask2.data.repositories

import com.kurilov.worktask2.data.api.RetrofitBuilder

object CharactersInternetLoader {

    private val apiService = RetrofitBuilder.apiService

    suspend fun getAllCharacters() = apiService.getAllCharacters()

    suspend fun getSingleCharacter(id : Int) = apiService.getSingleCharacter(id)

}