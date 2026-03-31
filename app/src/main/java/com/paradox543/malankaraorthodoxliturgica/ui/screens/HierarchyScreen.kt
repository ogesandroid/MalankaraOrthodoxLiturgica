package com.paradox543.malankaraorthodoxliturgica.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.paradox543.malankaraorthodoxliturgica.ui.components.TopNavBar

@Composable
fun HierarchyScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { TopNavBar("", navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {

        }

    }
}