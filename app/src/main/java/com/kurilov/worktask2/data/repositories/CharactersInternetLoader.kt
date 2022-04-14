package com.kurilov.worktask2.data.repositories

import com.kurilov.worktask2.data.api.RetrofitBuilder
import com.kurilov.worktask2.data.classes.Characther
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.kurilov.worktask2.data.common.Result

class CharactersInternetLoader {

    private val apiService = RetrofitBuilder.apiService

    suspend fun getAllCharacters(): Result<List<Characther>> {
        return try {
            val response = apiService.getAllCharacters()
            if (response.isSuccessful) {
                Result.Success(response.body()!!.results)
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }




    suspend fun getSingleCharacter(id : Int) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getSingleCharacter(id)
                if (response.isSuccessful) {
                    return@withContext Result.Success(response.body()!!)
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }

}