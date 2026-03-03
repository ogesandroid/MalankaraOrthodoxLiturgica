package com.paradox543.malankaraorthodoxliturgica.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.paradox543.malankaraorthodoxliturgica.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopNav(
    title: String = "malankara",
    navController: NavController,
) {
    val currentRoute =
        navController
            .currentBackStackEntryAsState()
            .value
            ?.destination
            ?.route

    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.StartEllipsis,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp),
                textAlign = TextAlign.Start,
            )
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Local Image",
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
            )
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                navigationIconContentColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.primary,
                actionIconContentColor = MaterialTheme.colorScheme.primary,
            ),
    )
}