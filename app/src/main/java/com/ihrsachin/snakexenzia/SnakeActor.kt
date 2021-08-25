package com.ihrsachin.snakexenzia

import android.graphics.*
import android.view.KeyEvent
import android.view.MotionEvent
import com.ihrsachin.gameengine.AbstractGamePanel
import com.ihrsachin.gameengine.Velocity
import com.ihrsachin.gameengine.actors.SimpleMovingActor
import java.util.ArrayList

class SnakeActor(x: Int, y: Int) : SimpleMovingActor(x, y, DRAW_SIZE, DRAW_SIZE) {
    var tailPos: ArrayList<Point>
    override fun stylePaint(p: Paint) {
        p.color = Color.GREEN
        p.style = Paint.Style.FILL
    }

    override fun draw(canvas: Canvas) {
        getPaint().setColor(Color.GREEN)
        canvas.drawRect(getRect(), getPaint())
        for (p in tailPos) {
            val r = Rect(p.x, p.y, p.x + this.getWidth(), p.y + this.getHeight())
            canvas.drawRect(r, getPaint())
        }
    }

    override fun move() {
        if (this.isEnabled()) {
            val headX: Int = getPoint().x
            val headY: Int = getPoint().y
            for (x in tailPos.size - 1 downTo 1) {
                tailPos[x][tailPos[x - 1].x] = tailPos[x - 1].y
            }
            tailPos[0][headX] = headY
            super.move()
        }
    }

    fun grow() {
        tailPos.add(Point(getX(), getY()))
    }

    fun checkBoundsCollision(panel: AbstractGamePanel): Boolean {
        if (this.getX() < 0) {
            return true
        } else if (this.getX() >= panel.getWidth() - this.getWidth()) {
            return true
        } else if (this.getY() < 0) {
            return true
        } else if (this.getY() >= panel.getHeight() - this.getHeight()) {
            return true
        }
        return false
    }

    fun handleKeyInput(keyCode: Int) {
        if (keyCode == KeyEvent.KEYCODE_W) {
            velocity.stop().setYDirection(Velocity.DIRECTION_UP).setYSpeed(STEP.toFloat())
        } else if (keyCode == KeyEvent.KEYCODE_S) {
            velocity.stop().setYDirection(Velocity.DIRECTION_DOWN).setYSpeed(STEP.toFloat())
        } else if (keyCode == KeyEvent.KEYCODE_A) {
            velocity.stop().setXDirection(Velocity.DIRECTION_LEFT).setXSpeed(STEP.toFloat())
        } else if (keyCode == KeyEvent.KEYCODE_D) {
            velocity.stop().setXDirection(Velocity.DIRECTION_RIGHT).setXSpeed(STEP.toFloat())
        }
    }

    fun handleTouchInput(event: MotionEvent) {
        if (getVelocity().getYSpeed() == 0f) {
            if (event.getY() < this.getY()) {
                getVelocity().stop().setYDirection(Velocity.DIRECTION_UP).setYSpeed(STEP.toFloat())
            } else if (event.getY() > this.getY() && getVelocity().getYSpeed() == 0f) {
                getVelocity().stop().setYDirection(Velocity.DIRECTION_DOWN).setYSpeed(STEP.toFloat())
            }
        } else if (getVelocity().getXSpeed() == 0f) {
            if (event.getX() < this.getX()) {
                getVelocity().stop().setXDirection(Velocity.DIRECTION_LEFT).setXSpeed(STEP.toFloat())
            } else if (event.getX() > this.getX()) {
                getVelocity().stop().setXDirection(Velocity.DIRECTION_RIGHT).setXSpeed(STEP.toFloat())
            }
        }
    }

    companion object {
        const val DRAW_SIZE = 25
        const val STEP = 25
    }

    init {
        getVelocity().stop().setXDirection(Velocity.DIRECTION_RIGHT).setXSpeed(STEP.toFloat())
        tailPos = ArrayList()
        tailPos.add(Point(x - this.getWidth(), y))
        tailPos.add(Point(x - this.getWidth() * 2, y))
    }
}