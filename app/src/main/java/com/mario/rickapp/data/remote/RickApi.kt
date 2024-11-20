package com.mario.rickapp.data.remote

import com.mario.rickapp.data.remote.dto.CharacterDto
import com.mario.rickapp.data.remote.dto.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterListDto

    @GET("character/{characterId}")
    suspend fun getCharacterDetail(@Path("characterId") characterId: Int): CharacterDto

}