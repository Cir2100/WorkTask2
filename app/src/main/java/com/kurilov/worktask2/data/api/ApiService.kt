package com.kurilov.worktask2.data.api

import com.kurilov.worktask2.data.classes.AllCharactersResult
import com.kurilov.worktask2.data.classes.Characther
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters() : Response<AllCharactersResult>

    @GET("character/{characterId}")
    suspend fun getSingleCharacters(@Path("characterId") characterId: Int) : Response<Characther>

    @GET("character/{characterId}")
    suspend fun getListCharacters(@Path("characterId") characterId: List<Int>) : Response<List<Characther>>

}