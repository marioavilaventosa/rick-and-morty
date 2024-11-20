package com.mario.rickapp.data.repository

import com.mario.rickapp.data.remote.RickApi
import com.mario.rickapp.data.remote.CharactersPagingSource
import com.mario.rickapp.domain.repository.RickRepository
import javax.inject.Inject
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mario.rickapp.data.remote.dto.CharacterDto
import com.mario.rickapp.domain.model.Character
import kotlinx.coroutines.flow.Flow

class RickRepositoryImpl @Inject constructor(
    private val api: RickApi
) : RickRepository {

    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharactersPagingSource(api) }
        ).flow
    }

    override suspend fun getCharacterDetail(characterId: Int): CharacterDto {
        return api.getCharacterDetail(characterId)
    }

}