package com.mario.rickapp.domain.repository

import androidx.paging.PagingData
import com.mario.rickapp.data.remote.dto.CharacterDto
import com.mario.rickapp.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface RickRepository {

    fun getCharacters(): Flow<PagingData<Character>>

    suspend fun getCharacterDetail(characterId: Int): CharacterDto

}