package com.anwesh.uiprojects.fourcrossboxview

/**
 * Created by anweshmishra on 25/05/19.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

val nodes : Int = 5
val lines : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#0D47A1")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawCrossBox(i : Int, size : Float, sc : Float, paint : Paint) {
    val sc1 : Float = sc.divideScale(0, 2)
    val sc2 : Float = sc.divideScale(1, 2)
    val a : Float = size / 2
    val b : Float = a * sc1
    val c : Float = 2 * size * sc2
    save()
    rotate(90f * i)
    drawLine(a, a, a + b, a + b, paint)
    drawLine(size, -size, size, -size + c, paint)
    restore()
}

fun Canvas.drawCBNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.style = Paint.Style.STROKE
    save()
    translate(w / 2, gap * (i + 1))
    rotate(90f * sc2)
    drawRect(RectF(-size / 2, -size / 2, size / 2, size / 2), paint)
    for (j in 0..(lines - 1)) {
        drawCrossBox(j, size, sc1.divideScale(j, lines), paint)
    }
    restore()
}

class FourCrossBoxView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}