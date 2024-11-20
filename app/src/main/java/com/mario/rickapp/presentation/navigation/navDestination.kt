package com.mario.rickapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object CharacterList

@Serializable
data class CharacterDetail(val characterId: Int)