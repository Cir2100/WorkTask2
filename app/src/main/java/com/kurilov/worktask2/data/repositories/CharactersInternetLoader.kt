package com.kurilov.worktask2.data.repositories

import android.util.Log
import com.kurilov.worktask2.data.api.RetrofitBuilder
import com.kurilov.worktask2.data.classes.Characther
import com.kurilov.worktask2.data.common.Result
import java.lang.Integer.min


class CharactersInternetLoader {

    private val apiService = RetrofitBuilder.apiService

    private var maxId = 0

    suspend fun getListCharacters(startId : Int): Result<List<Characther>> {
        if (startId == maxId && startId != 0) return Result.Success(listOf())
        return if (maxId == 0) {
            getAllCharacters()
        } else {
            getMoreCharacters(startId)
        }
    }

    suspend fun getSingleCharacter(id : Int) : Result<Characther> {
        return try {
            val response = apiService.getSingleCharacters(id)
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private suspend fun getAllCharacters(): Result<List<Characther>> {
        return try {
            val response = apiService.getAllCharacters()
            if (response.isSuccessful) {
                maxId = response.body()!!.info.count
                Result.Success(response.body()!!.results)
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private suspend fun getMoreCharacters(startId: Int): Result<List<Characther>>  {
        return try {
            val ids : List<Int> = (startId..min(startId+19, maxId)).toList()
            val response = apiService.getListCharacters(ids)
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}