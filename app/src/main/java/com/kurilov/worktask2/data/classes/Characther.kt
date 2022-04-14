package com.kurilov.worktask2.data.classes

data class Characther(
    val id : Int,
    val name : String,
    val status : String,
    val species : String,
    val gender : String,
    val image : String,
    val location : Location,
    val episode : List<String>
)
