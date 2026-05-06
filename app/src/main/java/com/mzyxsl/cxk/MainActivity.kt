package com.mzyxsl.cxk

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzyxsl.cxk.ui.theme.Chicken_Sound_BoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chicken_Sound_BoxTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    var currentPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    fun stopPlayer() {
        currentPlayer?.release()
        currentPlayer = null
    }

    fun playSound(resId: Int) {
        stopPlayer()
        MediaPlayer.create(context, resId).apply {
            currentPlayer = this
            start()
            setOnCompletionListener { mp ->
                mp.release()
                if (currentPlayer == mp) {
                    currentPlayer = null
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose { stopPlayer() }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.surface) { }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { stopPlayer() },
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    imageVector = Icons.Filled.Pause,
                    contentDescription = stringResource(R.string.stop)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 8.dp, vertical = 13.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            data class Btn(val label: Int, val sound: Int, val weight: Int = 1)

            listOf(
                listOf(
                    Btn(R.string.ji, R.raw.j),
                    Btn(R.string.ni, R.raw.n),
                    Btn(R.string.tai, R.raw.t),
                    Btn(R.string.mei, R.raw.m)
                ),
                listOf(
                    Btn(R.string.chang, R.raw.chang),
                    Btn(R.string.tiao, R.raw.tiao),
                    Btn(R.string.rap, R.raw.rp),
                    Btn(R.string.lq, R.raw.lq)
                ),
                listOf(
                    Btn(R.string.music, R.raw.mck),
                    Btn(R.string.ni2, R.raw.ni2),
                    Btn(R.string.gan, R.raw.gan),
                    Btn(R.string.amagi, R.raw.amagi)
                ),
                listOf(
                    Btn(R.string.ngm, R.raw.ngm),
                    Btn(R.string.ma, R.raw.ma2),
                    Btn(R.string.ganma, R.raw.ganma),
                    Btn(R.string.nhf, R.raw.nhf)
                ),
                listOf(
                    Btn(R.string.ngmhhy, R.raw.ngmhhy),
                    Btn(R.string.aa, R.raw.aa),
                    Btn(R.string.yhhmagi, R.raw.yhhmgn, 2)
                )
            ).forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    row.forEach { btn ->
                        Button(
                            onClick = { playSound(btn.sound) },
                            modifier = Modifier.weight(btn.weight.toFloat()),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = stringResource(btn.label),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

// 新增预览函数
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Chicken_Sound_BoxTheme {
        MainScreen()
    }
}