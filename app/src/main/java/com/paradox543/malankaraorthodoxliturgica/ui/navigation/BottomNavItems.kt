package com.paradox543.malankaraorthodoxliturgica.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.paradox543.malankaraorthodoxliturgica.R

val iconSize = 25.dp
val bottomNavItems =
    listOf(
        BottomNavItem("home", "Home") {
            Icon(painterResource(R.drawable.icon_home), "Home")
        },
        BottomNavItem("prayNow", "Pray Now") {
            Icon(
                painterResource(R.drawable.icon_pray),
                "Clock",
                modifier = Modifier.size(iconSize),
            )
        },
//        BottomNavItem(
//            "music",
//            "Music",
//        ) {
//            Icon(
//                painterResource(R.drawable.musical_note),
//                "Music",
//                modifier = Modifier.size(iconSize),
//            )
//        },
        /*BottomNavItem("calendar", "Calendar") {
            Icon(
                painterResource(R.drawable.calendar),
                "Calendar",
                Modifier.size(iconSize),
            )
        },*/
        BottomNavItem("locations", "Locations") {
            Icon(
                painterResource(R.drawable.ic_locations),
                "Locations",
                Modifier.size(iconSize),
            )
        },
        BottomNavItem("settings", "Settings") {
            Icon(
                painterResource(R.drawable.icon_settings),
                "Settings",
                modifier = Modifier.size(iconSize),
            )
        },
    )