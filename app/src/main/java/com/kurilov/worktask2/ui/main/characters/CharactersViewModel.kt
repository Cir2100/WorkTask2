package com.kurilov.worktask2.ui.main.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurilov.worktask2.data.classes.Characther
import com.kurilov.worktask2.data.repositories.CharactersRepo
import kotlinx.coroutines.launch
import com.kurilov.worktask2.data.common.Result

class CharactersViewModel : ViewModel() {

    private val charactersRepo = CharactersRepo

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _characters = MutableLiveData<List<Characther>>()
    val characters = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun loadCharacters(lastId : Int) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val charactersResult = charactersRepo.getListCharacters(lastId + 1)) {
                is Result.Success -> {
                    _characters.value = charactersResult.data!!
                    _dataLoading.postValue(false)
                }

                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _characters.value = emptyList()
                    _error.postValue(charactersResult.exception.message)
                }
            }
        }
    }
}