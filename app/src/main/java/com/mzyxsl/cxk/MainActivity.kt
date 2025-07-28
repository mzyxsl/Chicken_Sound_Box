package com.mzyxsl.cxk

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var currentPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.amige).setOnClickListener {
            playSound(R.raw.amagi)
        }

        findViewById<Button>(R.id.ngmhhy).setOnClickListener {
            playSound(R.raw.ngmhhy)
        }

        findViewById<Button>(R.id.ji).setOnClickListener {
            playSound(R.raw.j)
        }

        findViewById<Button>(R.id.ni).setOnClickListener {
            playSound(R.raw.n)
        }

        findViewById<Button>(R.id.tai).setOnClickListener {
            playSound(R.raw.t)
        }

        findViewById<Button>(R.id.mei).setOnClickListener {
            playSound(R.raw.m)
        }

        findViewById<Button>(R.id.chang).setOnClickListener {
            playSound(R.raw.chang)
        }

        findViewById<Button>(R.id.tiao).setOnClickListener {
            playSound(R.raw.tiao)
        }

        findViewById<Button>(R.id.rap).setOnClickListener {
            playSound(R.raw.rp)
        }

        findViewById<Button>(R.id.lq).setOnClickListener {
            playSound(R.raw.lq)
        }

        findViewById<Button>(R.id.music).setOnClickListener {
            playSound(R.raw.mck)
        }

        findViewById<Button>(R.id.yhhmagi).setOnClickListener {
            playSound(R.raw.yhhmgn)
        }

        findViewById<Button>(R.id.ngm).setOnClickListener {
            playSound(R.raw.ngm)
        }

        findViewById<Button>(R.id.nhf).setOnClickListener {
            playSound(R.raw.nhf)
        }
    }

    private fun playSound(resId: Int) {
        // 停止当前播放的音频
        currentPlayer?.release()

        // 创建并播放新音频
        MediaPlayer.create(this, resId).apply {
            currentPlayer = this
            start()
            setOnCompletionListener {
                it.release()
                if (currentPlayer == it) {
                    currentPlayer = null
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        currentPlayer?.release()
    }
}