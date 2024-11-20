package com.mario.rickapp.domain.usecase

import com.mario.rickapp.domain.model.Character
import com.mario.rickapp.domain.repository.RickRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import androidx.paging.PagingData

class GetCharactersUseCase @Inject constructor(
    private val repository: RickRepository
) {

    operator fun invoke(): Flow<PagingData<Character>> = flow {
        repository.getCharacters()
    }

}