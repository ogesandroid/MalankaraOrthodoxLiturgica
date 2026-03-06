package com.paradox543.malankaraorthodoxliturgica.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.paradox543.malankaraorthodoxliturgica.R
import com.paradox543.malankaraorthodoxliturgica.domain.prayer.model.PageNode
import com.paradox543.malankaraorthodoxliturgica.ui.components.TopNavBar
import com.paradox543.malankaraorthodoxliturgica.ui.navigation.AppScreen
import com.paradox543.malankaraorthodoxliturgica.ui.viewmodel.PrayerViewModel

@Composable
fun SectionListingScreen(
    navController: NavController,
    prayerViewModel: PrayerViewModel,
    node: PageNode,
    modifier: Modifier = Modifier,
) {

    val translations by prayerViewModel.translations.collectAsState()
    val nodes = node.children
    var title = ""
    for (item in node.route.split("_")) {
        title += (translations[item] ?: item) + " "
    }


    Scaffold(
        topBar = { TopNavBar(title, navController) }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, bottom = 0.dp, start = 0.dp, end = 0.dp),
            ) {
                items(nodes.size) { index ->
                    SectionCard(
                        nodes[index],
                        navController,
                        translations,
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionCard(
    node: PageNode,
    navController: NavController,
    translations: Map<String, String>,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp, start = 21.dp, end = 21.dp, top = 0.dp)
            .clickable {
                if (node.children.isNotEmpty()) {
                    navController.navigate(AppScreen.SectionList.createRoute(node.route))
                } else if (node.filename != null && node.filename?.endsWith(".json") == true) {
                    navController.navigate(AppScreen.Prayer.createRoute(node.route))
                } else if (node.type == "song" || (node.filename != null && node.filename!!.endsWith(
                        ".mp3"
                    ))
                ) {
                    navController.navigate(AppScreen.Song.createRoute(node.route))
                } else {
                    Log.w("SectionCard", "Invalid operation: Node has no children and no filename.")
                }
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD1422B))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val text = node.route.split("_").last()
            Text(
                text = if (text.contains("ragam")) {
                    translations["ragam"] + " " + text.substringAfter("ragam")
                } else {
                    translations[text] ?: text
                },
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_white_arrow_icon),
                contentDescription = "Local Image",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}