package com.paradox543.malankaraorthodoxliturgica.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.paradox543.malankaraorthodoxliturgica.R
import com.paradox543.malankaraorthodoxliturgica.domain.bible.model.BibleReference
import com.paradox543.malankaraorthodoxliturgica.domain.bible.model.ReferenceRange
import com.paradox543.malankaraorthodoxliturgica.domain.prayer.model.PageNode
import com.paradox543.malankaraorthodoxliturgica.services.InAppReviewManager
import com.paradox543.malankaraorthodoxliturgica.ui.components.BottomNavBar
import com.paradox543.malankaraorthodoxliturgica.ui.components.HomeTopNav
import com.paradox543.malankaraorthodoxliturgica.ui.navigation.AppScreen
import com.paradox543.malankaraorthodoxliturgica.ui.theme.CardBorderColor
import com.paradox543.malankaraorthodoxliturgica.ui.viewmodel.BibleViewModel
import com.paradox543.malankaraorthodoxliturgica.ui.viewmodel.PrayerViewModel


@SuppressLint("ContextCastToActivity")
@Composable
fun SectionScreen(
    navController: NavController,
    prayerViewModel: PrayerViewModel,
    bibleViewModel: BibleViewModel,
    node: PageNode,
    inAppReviewManager: InAppReviewManager,
    modifier: Modifier = Modifier,
) {
    val translations by prayerViewModel.translations.collectAsState()
    val menus by prayerViewModel.menuList.collectAsState()
    val banner by prayerViewModel.bannerImg.collectAsState()
    val isLoading by prayerViewModel.isLoading.collectAsState()
    val nodes = node.children
    Log.e("ahgsf",nodes.toString())
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    var title = ""
    for (item in node.route.split("_")) {
        title += (translations[item] ?: item) + " "
    }

    val activity = LocalContext.current as? Activity

    LaunchedEffect(activity) {
        // Notify ViewModel that screen opened
        prayerViewModel.onSectionScreenOpened()

        // Collect review request events
        prayerViewModel.requestReview.collect {
            activity?.let { inAppReviewManager.checkForReview(it) }
        }
    }

    Scaffold(
        topBar = { HomeTopNav(title, navController) },
        bottomBar = { BottomNavBar(navController = navController) }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (screenWidth > 600.dp) {
                    Row {
                        DisplayIconography(bibleViewModel,navController,"row", banner)
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(240.dp),
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            item {
                                LazyRow(
                                    contentPadding = PaddingValues(end = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(34.dp),
                                    modifier = Modifier.padding(
                                        bottom = 16.dp, top = 24.dp
                                    )
                                ) {
                                    items(menus) { menu ->

                                        //TODO: Need to pass the route from the api response.
                                        MenuListCard(navController,menu.name, menu.icon, AppScreen.Calendar.route)
                                    }
                                }
                            }
                            items(nodes.size) { index ->
                                SectionCard(
                                    nodes[index],
                                    navController,
                                    translations,
                                )
                            }
                        }
                    }
                } else {
                    Column {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(240.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(0.6f),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            item {
                                DisplayIconography(bibleViewModel,navController,"column", banner)
                            }
                            item {
                                LazyRow(
                                    contentPadding = PaddingValues(end = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(34.dp),
                                    modifier = Modifier.padding(
                                        bottom = 16.dp, top = 24.dp, start = 21.dp, end = 21.dp
                                    )
                                ) {
                                    items(menus) { menu ->
                                        //TODO: Need to pass the route from the api response.
                                        MenuListCard(navController,menu.name, menu.icon,AppScreen.Calendar.route)
                                    }
                                }
                            }
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
        }
    }
}

@Composable
private fun DisplayIconography(bibleViewModel: BibleViewModel, navController: NavController, orientation: String, banner: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 21.dp, end = 21.dp, top = 21.dp, bottom = 0.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (banner.isNotEmpty()) {
                AsyncImage(
                    model = banner[0],
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(164.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.greatlent),
                    contentDescription = "Local Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(164.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = "\"For I know the plans I have for you,\" declares the Lord, \"plans to prosper you and not to harm you, plans to give you hope and a future.\"",
                color = Color(0xFF555353),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 10.dp, bottom = 0.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 12.dp, top = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Jeremiah 29:11",
                    color = Color(0xFFD1422B),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            //TODO: Need data from the api response.
                            val entries = listOf(
                                BibleReference(
                                    bookNumber = 1,
                                    ranges = listOf(ReferenceRange(startChapter = 5, startVerse = 1, endChapter = 5, endVerse = 12)),
                                ),
                            )
                            bibleViewModel.setSelectedBibleReference(entries) // Pass the data to the bible model to get the readings.
                            navController.navigate(AppScreen.BibleReader.route) // Redirection to bible reading page.
                        }
                    ) {
                        Text(
                            text = "Read More ",
                            color = Color(0xFF0D6BD7),
                            textDecoration = TextDecoration.Underline,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Icon(
                            painterResource(R.drawable.ic_right_arrow),
                            tint = Color(0xFF0D6BD7),
                            contentDescription = "Arrow",
                        )
                    }
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
                    Log.d("SectionCard", "Navigating to section: ${node.route}")
                    navController.navigate(AppScreen.Section.createRoute(node.route))
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
            val icon: Int
            when (node.route) {
                "commonPrayers" -> {
                    icon = R.drawable.ic_common_prayer_icon
                }

                "dailyPrayers" -> {
                    icon = R.drawable.ic_dialy_prayer_icon
                }

                "sacraments" -> {
                    icon = R.drawable.ic_sacramentral_prayer_icon
                }

                "ekkaraSongs" -> {
                    icon = R.drawable.ic_ekara_prayer_icon
                }

                else -> {
                    icon = 0
                }
            }
            if (icon != 0) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "Local Image",
                    modifier = Modifier
                        .width(43.dp)
                        .height(43.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
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

@Composable
fun MenuListCard(navController: NavController ,menuName: String, icon: String, route: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .width(62.dp)
                .height(62.dp)
                .clickable{
                    //TODO: Need to get the route from api response.
                    navController.navigate(route)
                },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, color = CardBorderColor),
            elevation = CardDefaults.cardElevation(0.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = icon,
                    contentDescription = "Menu Icon",
                    modifier = Modifier
                        .width(38.dp)
                        .height(38.dp)
                )
            }
        }
        Text(
            text = menuName,
            color = Color(0xFFD1422B),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 6.dp, start = 5.dp, end = 5.dp, bottom = 0.dp)
        )
    }
}

