package com.example.mysocialapp.android.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator

) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "HomeScreen")
    }
}