package com.github.chanjungkim.viewpagerwithlottie

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.github.chanjungkim.viewpagerwithlottie.ui.theme.ViewpagerWIthLottieTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewpagerWIthLottieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SampleScreen()
                }
            }
        }
    }
}

@Composable
fun SampleScreen(){
    val lottieList = listOf(R.raw.intro_1, R.raw.intro_2, R.raw.intro_1, R.raw.intro_2, R.raw.intro_1, R.raw.intro_2)

    MaterialTheme {
        LottieViewPager(
            lottieList,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LottieViewPager(
    contentList: List<Int>,
    modifier: Modifier = Modifier
){
    val pagerState = rememberPagerState()

    Column {
        HorizontalPager(
            count = contentList.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            userScrollEnabled = true
        ) {
            ContentItem(contentList[pagerState.currentPage])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(bottom = 8.dp),
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = Color.Gray
        )
    }
}

@Composable
fun ContentItem(
    @RawRes lottieRawId: Int
){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRawId))
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = true,
        speed = 1f,
        iterations = 1
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.wrapContentSize()
    )
}