package com.kurilov.worktask2.ui.main.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurilov.worktask2.data.classes.Characther
import com.kurilov.worktask2.data.common.Result
import com.kurilov.worktask2.data.repositories.CharactersRepo
import kotlinx.coroutines.launch

class InfoViewModel: ViewModel() {

    private val charactersRepo = CharactersRepo

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _character = MutableLiveData<Characther?>()
    val character = _character

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getCharacterInfo(id: Int) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val charactersResult = charactersRepo.getSingleCharacter(id)) {
                is Result.Success -> {
                    _character.value = charactersResult.data
                    _dataLoading.postValue(false)
                }

                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _character.value = null
                    _error.postValue(charactersResult.exception.message)
                }
            }
        }
    }


}