package com.mario.rickapp.presentation.characterList.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import com.mario.rickapp.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mario.rickapp.domain.model.Character
import com.mario.rickapp.ui.theme.Black
import com.mario.rickapp.ui.theme.Gray

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(
    character: Character,
    onItemClick: (Character) -> Unit
) {

    Card(
        modifier = Modifier
            .shadow(
                elevation = 10.dp
            )
            .border(1.dp, Black, RoundedCornerShape(12.dp))
            .clickable {
                onItemClick(character)
            },
        colors = CardDefaults.cardColors(
            containerColor = Gray
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            GlideImage(
                model = character.image,
                contentDescription = stringResource(R.string.character_image),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.3f)
                    .clip(
                        RoundedCornerShape(bottomStart = 12.dp , topStart = 12.dp)
                    ),
                contentScale = ContentScale.Crop
            )


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = stringResource(R.string.origin),
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = character.origin ?: stringResource(R.string.unknown),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = stringResource(R.string.status),
                    style = MaterialTheme.typography.bodySmall
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatusItem(character)
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = "-",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = character.species.type,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
        }
    }
}