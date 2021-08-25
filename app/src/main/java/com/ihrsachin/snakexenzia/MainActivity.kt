package com.ihrsachin.snakexenzia

import android.os.Bundle

class MainActivity : com.ihrsachin.gameengine.GameActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //before activity is created : cold start
        //switch back to original Theme (App Theme)
        setTheme(R.style.AppTheme)
        switchFullscreen()
        setContentView(SnakeGamePanel(this))
    }
}