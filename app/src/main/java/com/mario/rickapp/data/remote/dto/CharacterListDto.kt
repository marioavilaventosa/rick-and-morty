package com.mario.rickapp.data.remote.dto

data class CharacterListDto (
    val info: InfoDto,
    val results: List<CharacterDto>
)