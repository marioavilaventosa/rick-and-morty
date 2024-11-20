package com.mario.rickapp.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mario.rickapp.R
import com.mario.rickapp.presentation.characterList.components.StatusItem
import com.mario.rickapp.ui.theme.Black
import com.mario.rickapp.ui.theme.Blue
import com.mario.rickapp.ui.theme.White

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    navController: NavController,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(characterId) {
        viewModel.getCharacterDetail(characterId)
    }

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        state.character?.let { character ->
            Box(modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
            ) {
                GlideImage(
                    model = character.image,
                    contentDescription = stringResource(R.string.character_image),
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                        ),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp)
                        .background(
                            Black,
                            CircleShape
                        )
                        .size(36.dp)
                        .align(Alignment.TopStart)
                        .clickable{
                            navController.popBackStack()
                        }
                ) {

                    Icon(
                        painter = painterResource(R.drawable.left_arrow),
                        tint = White,
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier
                            .padding(8.dp)

                    )
                }
            }

            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = character.name,
                style = MaterialTheme.typography.headlineLarge
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                StatusItem(character)
            }

            Row(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = character.species.type,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = stringResource(R.string.species),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val gender =  when (character.gender) {
                        true -> R.string.male
                        false -> R.string.female
                        else -> R.string.unknown
                    }
                    Text(
                        text = stringResource(gender),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = stringResource(R.string.gender),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Text(
                modifier = Modifier
                    .padding(top = 24.dp),
                text = stringResource(R.string.location),
                style = MaterialTheme.typography.bodyMedium
            )

            character.origin?.let {
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    text = character.origin,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            character.location?.let {
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    text = character.location,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

    if(state.error.isNotBlank()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = state.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
            )
        }
    }

    if(state.isLoading) {
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