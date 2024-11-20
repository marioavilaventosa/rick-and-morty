package com.mario.rickapp.domain

import app.cash.turbine.test
import com.mario.rickapp.common.Resource
import com.mario.rickapp.data.remote.dto.CharacterDto
import com.mario.rickapp.data.remote.dto.toCharacter
import com.mario.rickapp.domain.repository.RickRepository
import com.mario.rickapp.domain.usecase.GetCharacterDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCharacterDetailUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: RickRepository

    lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase
    val characterId = 1
    private val mockCharacterDto = mockk<CharacterDto>(relaxed = true)
    private val mockCharacter = mockCharacterDto.toCharacter()


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCharacterDetailUseCase = GetCharacterDetailUseCase(repository)
    }


    @Test
    fun `invoke emits success when repository returns characters`() = runTest {
        //Given
        coEvery { repository.getCharacterDetail(characterId) } returns mockCharacterDto

        //Then
        getCharacterDetailUseCase(characterId).test {
            awaitItem()
            val success = awaitItem() as Resource.Success
            assertEquals(mockCharacter, success.data)
            awaitComplete()
        }
        coVerify { repository.getCharacterDetail(characterId) }

    }
}