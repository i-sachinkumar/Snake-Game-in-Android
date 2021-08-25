package com.ihrsachin.snakexenzia

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.KeyEvent
import android.view.MotionEvent
import com.ihrsachin.gameengine.AbstractGamePanel

class SnakeGamePanel(context: Context?) : AbstractGamePanel(context) {

    private var snake: SnakeActor? = null
    private var apple: AppleActor? = null
    private var score: ScoreBoard? = null
    private var isPaused = false
    override fun onStart() {
        snake = SnakeActor(100, 100)
        apple = AppleActor(200, 50)
        score = ScoreBoard(this)
    }

    override fun onTimer() {
        if (!isPaused) {
            if (snake!!.checkBoundsCollision(this)) {
                snake!!.setEnabled(false)
            }
            snake!!.move()
            if (apple!!.intersect(snake)) {
                snake!!.grow()
                score!!.earnPoints(50)
                apple!!.reposition(this)
            }
        }
    }

    override fun redrawCanvas(canvas: Canvas) {
        if (snake!!.isEnabled()) {
            snake!!.draw(canvas)
            apple!!.draw(canvas)
            score!!.draw(canvas)
        } else {
            val p: Paint = getPaint()
            p.textSize = 50f
            p.color = Color.BLACK
            canvas.drawText("Game over!", 100f, 100f, p)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        snake!!.handleKeyInput(keyCode)
        if (keyCode == KeyEvent.KEYCODE_G) {
            onStart()
        }
        if (keyCode == KeyEvent.KEYCODE_P) {
            isPaused = !isPaused
        }
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            snake!!.handleTouchInput(event)
            return true
        }
        return false
    }
}