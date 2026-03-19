package com.paradox543.malankaraorthodoxliturgica.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.core.graphics.toColorInt

@SuppressLint("SetJavaScriptEnabled")
class VideoDialog(context: Context, private val videoUrl: String) :
    android.app.Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val videoId = extractYouTubeId(videoUrl)

        val webView = WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.mediaPlaybackRequiresUserGesture = false
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            settings.userAgentString =
                "Mozilla/5.0 (Linux; Android 13; Pixel 7) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/120.0.6099.230 Mobile Safari/537.36"
            webChromeClient = WebChromeClient()

            val html = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        * { margin:0; padding:0; background:#000; }
                        html, body { width:100%; height:100%; }
                        #player { width:100%; height:100%; }
                    </style>
                </head>
                <body>
                    <div id="player"></div>
                    <script>
                        var tag = document.createElement('script');
                        tag.src = "https://www.youtube.com/iframe_api";
                        document.head.appendChild(tag);
                        function onYouTubeIframeAPIReady() {
                            new YT.Player('player', {
                                videoId: '$videoId',
                                playerVars: {
                                    autoplay: 1,
                                    playsinline: 1,
                                    controls: 1,
                                    rel: 0,
                                    origin: 'https://www.youtube-nocookie.com'
                                },
                                events: {
                                    onReady: function(e) { e.target.playVideo(); }
                                }
                            });
                        }
                    </script>
                </body>
                </html>
            """.trimIndent()

            loadDataWithBaseURL(
                "https://www.youtube-nocookie.com",
                html,
                "text/html",
                "utf-8",
                null
            )
        }
        val closeButton = android.widget.ImageButton(context).apply {
            setImageDrawable(
                androidx.core.content.ContextCompat.getDrawable(
                    context,
                    android.R.drawable.ic_menu_close_clear_cancel
                )
            )
            setBackgroundColor("#99000000".toColorInt())
            setPadding(16, 16, 16, 16)
            setOnClickListener { dismiss() }
        }
        val frameLayout = android.widget.FrameLayout(context).apply {
            setBackgroundColor(android.graphics.Color.BLACK)
            addView(
                webView,
                android.widget.FrameLayout.LayoutParams(
                    android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
                    android.widget.FrameLayout.LayoutParams.MATCH_PARENT
                )
            )
            addView(
                closeButton,
                android.widget.FrameLayout.LayoutParams(
                    120,
                    120,
                    android.view.Gravity.TOP or android.view.Gravity.END
                ).apply {
                    topMargin = 32
                    rightMargin = 32
                }
            )
        }

        setContentView(frameLayout)
    }
}
fun extractYouTubeId(url: String): String? {
    val patterns = listOf(
        "v=([^&]+)",           // youtube.com/watch?v=ID
        "youtu.be/([^?]+)",    // youtu.be/ID
        "embed/([^?]+)"        // youtube.com/embed/ID
    )
    for (pattern in patterns) {
        val match = Regex(pattern).find(url)
        if (match != null) return match.groupValues[1]
    }
    return null
}