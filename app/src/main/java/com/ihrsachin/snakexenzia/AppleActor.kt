package com.ihrsachin.snakexenzia


import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.ihrsachin.gameengine.AbstractGamePanel
import com.ihrsachin.gameengine.actors.PositionedActor


class AppleActor(x: Int, y: Int) : PositionedActor(x, y, DRAW_SIZE, DRAW_SIZE) {
    override fun stylePaint(p: Paint) {
        p.color = Color.RED
        p.style = Paint.Style.FILL
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(getRectF(), 10f, 10f, getPaint())
    }

    fun reposition(panel: AbstractGamePanel) {
        setPos(randomCoordForPanel(panel.getWidth()), randomCoordForPanel(panel.getHeight()))
    }

    protected fun randomCoordForPanel(max: Int): Int {
        val multiplier = max / DRAW_SIZE
        val randomCoordinate = (Math.random() * multiplier).toInt()
        return randomCoordinate * DRAW_SIZE
    }

    companion object {
        const val DRAW_SIZE = 25
    }
}
