package com.paradox543.malankaraorthodoxliturgica.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.paradox543.malankaraorthodoxliturgica.R
import com.paradox543.malankaraorthodoxliturgica.ui.navigation.AppScreen
import com.paradox543.malankaraorthodoxliturgica.ui.navigation.bottomNavItems

@Composable
fun BottomNavBar(navController: NavController) {
    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    // Split nav items: 2 left, scan in centre, 2 right
    val leftItems = bottomNavItems.take(2)
    val rightItems = bottomNavItems.drop(2)

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        // The actual nav bar background row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(74.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            // Left items
            leftItems.forEach { item ->
                NavItem(
                    icon = item.icon,
                    label = item.label,
                    selected = currentRoute == item.route,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(item.route) { inclusive = true }
                        }
                    },
                )
            }

            // Centre spacer for the raised scan button
            Box(modifier = Modifier.weight(1f))

            // Right items
            rightItems.forEach { item ->
                NavItem(
                    icon = item.icon,
                    label = item.label,
                    selected = currentRoute == item.route,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(item.route) { inclusive = true }
                        }
                    },
                )
            }
        }

        // Raised Scan button centred, lifted above the bar
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-16).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { navController.navigate(AppScreen.QrScanner.route) },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_scan),
                    contentDescription = "Scan QR",
                    tint = Color.Gray,
                    modifier = Modifier.size(26.dp),
                )
            }
            Text(
                text = "Scan",
                color = Color.Gray,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth(0.18f)
                    .padding(bottom = 4.dp),
            )
        }
    }
}

@Composable
private fun NavItem(
    icon: @Composable () -> Unit,
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val tint = if (selected)
        Color(0xFFD1422B)
    else
        Color.Gray

    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CompositionLocalProvider(LocalContentColor provides tint) {
            icon()
        }
        Text(
            text = label,
            color = tint,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
        )
    }
}