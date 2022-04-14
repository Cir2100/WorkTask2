package com.kurilov.worktask2.data.api

import com.kurilov.worktask2.data.classes.Characther
import com.kurilov.worktask2.data.classes.MResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters() : MResult<Characther>

    @GET("character/{characterId}")
    suspend fun getSingleCharacter(@Path("characterId") characterId: Int) : MResult<Characther>

}