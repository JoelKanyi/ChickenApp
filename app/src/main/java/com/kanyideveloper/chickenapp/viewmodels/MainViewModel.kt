package com.kanyideveloper.chickenapp.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.chickenapp.data.Chicken
import com.kanyideveloper.chickenapp.data.ChickensApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val chickensApi: ChickensApi
) : ViewModel() {

    data class ChickenState(
        val chickens: List<Chicken>,
        val isLoading: Boolean = false
    )

    private val _state = mutableStateOf(ChickenState(emptyList()))
    val state: State<ChickenState> = _state

    init {
        getChickens()
    }

    private fun getChickens() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    chickens = chickensApi.getChickens(),
                    isLoading = false
                )
            } catch (e: Exception) {
                Log.d("MainViewModel", "getChickens: ", e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }
}