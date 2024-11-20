package com.mario.rickapp.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario.rickapp.common.Resource
import com.mario.rickapp.domain.usecase.GetCharacterDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CharacterDetailState())
    val state: State<CharacterDetailState> = _state

    fun getCharacterDetail(charactedId: Int) {
        getCharacterDetailUseCase(charactedId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CharacterDetailState(character = result.data)
                }
                is Resource.Error -> {
                    _state.value = CharacterDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CharacterDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}