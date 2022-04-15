package com.kurilov.worktask2.data.repositories

object CharactersRepo {

    private val charactersInternetLoader = CharactersInternetLoader()

    suspend fun getListCharacters(startId : Int) = charactersInternetLoader.getListCharacters(startId)

    suspend fun getSingleCharacter(id : Int) = charactersInternetLoader.getSingleCharacter(id)
}