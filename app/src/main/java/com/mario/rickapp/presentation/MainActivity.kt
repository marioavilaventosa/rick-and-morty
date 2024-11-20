package com.mario.rickapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.mario.rickapp.domain.model.Character
import com.mario.rickapp.presentation.characterList.CharacterListScreen
import com.mario.rickapp.presentation.detail.CharacterDetailScreen
import com.mario.rickapp.presentation.navigation.CharacterDetail
import com.mario.rickapp.presentation.navigation.CharacterList
import com.mario.rickapp.ui.theme.RickAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAppTheme {
                Surface() {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = CharacterList
                    ) {
                        composable<CharacterList> {
                            CharacterListScreen(
                                { character ->
                                    navController.navigate(CharacterDetail(character.id))
                                }
                            )
                        }
                        composable<CharacterDetail> { backStackEntry ->
                            val detail = backStackEntry.toRoute<CharacterDetail>()
                            CharacterDetailScreen(
                                detail.characterId,
                                navController
                            )
                        }
                    }
                }
            }
        }
    }
}