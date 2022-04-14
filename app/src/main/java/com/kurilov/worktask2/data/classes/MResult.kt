package com.kurilov.worktask2.data.classes

sealed class MResult<out R> {

    data class Success<out T>(val data: T) : MResult<T>()
    data class Error(val exception: Exception) : MResult<Nothing>()

}