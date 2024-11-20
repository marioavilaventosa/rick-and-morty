package com.mario.rickapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mario.rickapp.data.remote.dto.toCharacter
import retrofit2.HttpException
import java.io.IOException
import  com.mario.rickapp.domain.model.Character

class CharactersPagingSource(
    private val api: RickApi
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        return try {
            val response = api.getCharacters(page)
            val characters = response.results

            LoadResult.Page(
                data = characters.map { it.toCharacter() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (characters.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
