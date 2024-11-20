package com.mario.rickapp.data

import androidx.paging.PagingSource
import com.mario.rickapp.data.remote.CharactersPagingSource
import com.mario.rickapp.data.remote.RickApi
import com.mario.rickapp.data.remote.dto.CharacterDto
import com.mario.rickapp.data.remote.dto.CharacterListDto
import com.mario.rickapp.data.remote.dto.InfoDto
import com.mario.rickapp.data.remote.dto.LocationDto
import com.mario.rickapp.data.remote.dto.toCharacter
import com.mario.rickapp.domain.repository.RickRepository
import io.mockk.coEvery
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import com.mario.rickapp.domain.model.Character
import com.mario.rickapp.domain.model.CharacterSpecie
import io.mockk.mockk
import junit.framework.TestCase.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterPagingSourceTest {

    @RelaxedMockK
    private lateinit var api: RickApi

    private lateinit var pagingSource: CharactersPagingSource
    private val characterDto = CharacterDto(
        id = 1,
        name = "Rick",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = LocationDto(
            "",
            ""
        ),
        location = LocationDto(
            "",
            ""
        ),
        image = "",
        episode = listOf(),
        url = "",
        created = ""
    )

    private val mockCharactersDto = listOf(
        characterDto,
        characterDto,
        characterDto
    )

    private val mockCharacters = mockCharactersDto.map { it.toCharacter() }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pagingSource = CharactersPagingSource(api)
    }

    @Test
    fun `load returns page when successful`() = runTest {
        // Given
        val page = 1
        coEvery { api.getCharacters(page) } returns CharacterListDto(
            info = InfoDto(1, 1, "", ""),
            results = mockCharactersDto
        )

        // When
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = page, loadSize = 3, placeholdersEnabled = false)
        )

        // Then
        val expected = PagingSource.LoadResult.Page(
            data = mockCharacters,
            prevKey = null,
            nextKey = 2
        )
        assertEquals(expected, result)
    }


}
