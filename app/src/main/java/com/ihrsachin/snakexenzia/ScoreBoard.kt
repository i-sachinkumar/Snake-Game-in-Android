package com.ihrsachin.snakexenzia

import android.graphics.Canvas
import android.graphics.Paint
import com.ihrsachin.gameengine.AbstractGamePanel
import com.ihrsachin.gameengine.actors.PositionedActor

class ScoreBoard(context: AbstractGamePanel) : PositionedActor(context.getWidth() - 150, 30) {
    private var score = 0
    override fun stylePaint(p: Paint) {
        p.textSize = 20f
    }

    fun earnPoints(points: Int) {
        score += points
    }

    override fun draw(canvas: Canvas) {
        canvas.drawText("Score: $score", x.toFloat(), y.toFloat(), paint)
    }
}