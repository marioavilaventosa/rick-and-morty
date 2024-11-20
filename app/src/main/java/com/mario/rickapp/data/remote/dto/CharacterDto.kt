package com.mario.rickapp.data.remote.dto

import com.mario.rickapp.domain.model.Character
import com.mario.rickapp.domain.model.CharacterSpecie

data class CharacterDto (
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationDto,
    val location: LocationDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

fun CharacterDto.toCharacter() = Character(
    id = id,
    name = name,
    status = getCharacterStatus(status),
    species = String.toCharacterSpecie(species),
    type = type,
    gender = getCharacterGender(gender),
    origin = getLocationOrNull(origin.name),
    location = getLocationOrNull(location.name),
    image = image
)

fun getCharacterGender(gender: String): Boolean? = when (gender) {
    "Male" -> true
    "Female" -> true
    else -> null
}


fun getCharacterStatus(status: String): Boolean? = when (status) {
    "Alive" -> true
    "Dead" -> true
    else -> null
}

fun String.Companion.toCharacterSpecie(value: String): CharacterSpecie {
    return when (value) {
        "Human" -> CharacterSpecie.HUMAN
        "Alien" -> CharacterSpecie.ALIEN
        "Robot" -> CharacterSpecie.ROBOT
        "Humanoid" -> CharacterSpecie.HUMANOID
        else -> CharacterSpecie.CREATURE
    }
}

fun getLocationOrNull(location: String): String? = if (location != "unknown") {
    location
} else {
    null
}