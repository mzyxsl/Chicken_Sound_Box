package com.mzyxsl.cxk

import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {

    private var currentPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyDynamicColorIfpossible()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))  { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

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
        findViewById<Button>(R.id.aa).setOnClickListener {
            playSound(R.raw.aa)
        }
        findViewById<Button>(R.id.ni2).setOnClickListener {
            playSound(R.raw.ni2)
        }
        findViewById<Button>(R.id.gan).setOnClickListener {
            playSound(R.raw.gan)
        }
        findViewById<Button>(R.id.ma).setOnClickListener {
            playSound(R.raw.ma2)
        }
        findViewById<Button>(R.id.ganma).setOnClickListener {
            playSound(R.raw.ganma)
        }
        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.stopbutton) .setOnClickListener{
            currentPlayer?.release()
        }
    }

    private fun applyDynamicColorIfpossible() {
        DynamicColors.applyToActivityIfAvailable(this)
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