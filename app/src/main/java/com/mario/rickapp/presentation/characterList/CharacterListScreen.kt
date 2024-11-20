package com.mario.rickapp.presentation.characterList

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.mario.rickapp.presentation.characterList.components.CharacterItem
import com.mario.rickapp.R
import com.mario.rickapp.domain.model.Character
import com.mario.rickapp.ui.theme.Blue

@SuppressLint("SuspiciousIndentation")
@Composable
fun CharacterListScreen(
    onCharacterClicked: (Character) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()

    when {
        characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Blue
                )
            }
        }

        characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = stringResource(R.string.no_characters)
                )
            }
        }

        characters.loadState.hasError -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = stringResource(R.string.error)
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 12.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .width(200.dp),
                            painter = painterResource(R.drawable.rick_and_morty),
                            contentDescription = stringResource(R.string.app_name)
                        )
                    }
                }

                items(characters.itemCount) {
                    characters[it]?.let { characterModel ->
                        CharacterItem(
                            character = characterModel,
                            onItemClick = onCharacterClicked
                        )
                    }
                }
            }

            if (characters.loadState.append is LoadState.Loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center),
                        color = Blue
                    )
                }
            }
        }
    }
}