package com.kurilov.worktask2.data.repositories

import com.kurilov.worktask2.data.api.RetrofitBuilder

object CharactersRepo {

    private val charactersInternetLoader = CharactersInternetLoader()

    suspend fun getAllCharacters() = charactersInternetLoader.getAllCharacters()

    suspend fun getSingleCharacter(id : Int) = charactersInternetLoader.getSingleCharacter(id)
}