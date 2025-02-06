package dev.xtec

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

import compose_coroutine.composeapp.generated.resources.Res
import compose_coroutine.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        FetchComposable()
    }
}

@Composable
fun FetchComposable() {
    var isLoading by remember { mutableStateOf(false) }
    var data by remember { mutableStateOf(listOf<String>()) }

    // Definim un LaunchEffect per realitzar una operació asícnrona que tardarà molt de temps en completar-se
    // LaunchEffect es cancelarà i és tornarà a executar si `isLoading` canvia.
    LaunchedEffect(isLoading) {
        if (isLoading) {
            // Realitza una tasca que tarda molt de temps en executar-se, com obtenir dades desde una xarxa
            val newData = fetchData()
            // Actualitzem l'estat amb les noves dades
            data = newData
            isLoading = false
        }
    }

    Column {
        Button(onClick = { isLoading =true}) {
            Text("Fetch Data")
        }
        if (isLoading) {
            // Mostra un indicador de progress
            CircularProgressIndicator()
        } else {
            // Mostra les dades
            LazyColumn {
                items(data) { item ->
                    Text(item)
                }
            }
        }
    }
}

suspend fun fetchData(): List<String> {
    // Simula una operació de xarxa que tarda molt de temps en completar-se
    return listOf("Data 1", "Data 2", "Data 3")
}