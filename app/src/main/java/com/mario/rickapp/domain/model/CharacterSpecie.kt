package com.mario.rickapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class CharacterSpecie(val type: String) {
    HUMAN("Humano"),
    ALIEN("Alien"),
    CREATURE("Criatura Mitológica"),
    ROBOT("Robot"),
    HUMANOID("Humanoide")
}