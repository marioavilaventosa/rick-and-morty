package com.mario.rickapp.domain.usecase

import com.mario.rickapp.common.Resource
import com.mario.rickapp.data.remote.dto.toCharacter
import com.mario.rickapp.domain.model.Character
import com.mario.rickapp.domain.repository.RickRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: RickRepository
) {

    operator fun invoke(characterId: Int): Flow<Resource<Character>> = flow {
        try {
            emit(Resource.Loading())
            val character = repository.getCharacterDetail(characterId).toCharacter()
            emit(Resource.Success(character))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}