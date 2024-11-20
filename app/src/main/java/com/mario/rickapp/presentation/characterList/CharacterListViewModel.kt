package com.mario.rickapp.presentation.characterList

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mario.rickapp.domain.model.Character
import com.mario.rickapp.domain.repository.RickRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val rickRepository: RickRepository
) : ViewModel() {

    val characters: Flow<PagingData<Character>> = rickRepository.getCharacters()

}