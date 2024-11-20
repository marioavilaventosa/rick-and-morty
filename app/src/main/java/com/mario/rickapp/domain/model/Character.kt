package com.mario.rickapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Character (
    val id: Int,
    val name: String,
    val status: Boolean?,
    val species: CharacterSpecie,
    val type: String,
    val gender: Boolean?,
    val origin: String?,
    val location: String?,
    val image: String
)