package com.mario.rickapp.presentation.characterList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mario.rickapp.R
import com.mario.rickapp.domain.model.Character
import com.mario.rickapp.ui.theme.Green
import com.mario.rickapp.ui.theme.Red

@Composable
fun StatusItem(
    character: Character
) {
    character.status?.let { status ->
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(
                    color = if (status) Green else Red,
                    shape = CircleShape
                )
        )
        Text(
            modifier = Modifier.padding(start = 6.dp),
            text = stringResource(
                if (status) R.string.alive else R.string.dead
            ),
            style = MaterialTheme.typography.bodyMedium
        )
    } ?: run {
        Image(
            painterResource(R.drawable.unknown),
            contentDescription = stringResource(R.string.character_status),
            modifier = Modifier
                .size(16.dp)
        )
        Text(
            modifier = Modifier.padding(start = 6.dp),
            text = stringResource(R.string.unknown),
            style = MaterialTheme.typography.bodyMedium
        )

    }
}