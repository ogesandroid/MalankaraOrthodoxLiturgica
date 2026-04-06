package com.paradox543.malankaraorthodoxliturgica.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.paradox543.malankaraorthodoxliturgica.R
import com.paradox543.malankaraorthodoxliturgica.Utility.getYoutubeThumbnail
import com.paradox543.malankaraorthodoxliturgica.domain.video.model.VideoListingModel
import com.paradox543.malankaraorthodoxliturgica.ui.components.TopNavBar
import com.paradox543.malankaraorthodoxliturgica.ui.theme.Background
import com.paradox543.malankaraorthodoxliturgica.ui.viewmodel.VideoViewModel

@Composable
fun VideosScreen(
    navController: NavController,
    videoViewModel: VideoViewModel
) {
    val isLoading by videoViewModel.isLoading.collectAsState()
    val videoData by videoViewModel.videoData.collectAsState()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = { TopNavBar("", navController) }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 20.dp)
                    ) {
                        items(videoData) { item ->
                            VideoListCard(item, context) {
                                VideoDialog(context, item.link).show()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VideoListCard(
    videoItem: VideoListingModel.Data,
    context: Context,
    onPlayClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 7.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(Background)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 18.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(95.dp)
                        .height(73.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF1A1A2E))
                        .clickable { onPlayClick() },
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = getYoutubeThumbnail(videoItem.link),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color.Transparent, CircleShape)
                            .border(1.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = videoItem.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = videoItem.date,
                        fontSize = 14.sp,
                        color = Color(0xFF9E9E9E)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW, videoItem.link.toUri())
                            context.startActivity(intent)
                        }
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_open_inbrowser_icon),
                            contentDescription = "Open in Browser",
                            tint = Color(0xFF005DC8),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Open in Browser",
                            color = Color(0xFF005DC8),
                            fontSize = 14.sp
                        )
                    }
                }
            }
            HorizontalDivider(color = Color(0xFFC9CACA), thickness = 1.dp)
        }
    }
}



