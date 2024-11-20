package com.mario.rickapp.presentation.detail

import com.mario.rickapp.domain.model.Character

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String = ""
)
