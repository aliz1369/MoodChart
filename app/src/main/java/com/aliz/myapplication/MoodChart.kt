package com.aliz.myapplication

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import java.lang.Integer.min
import java.lang.Math.cos
import java.lang.Math.sin

private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);

    fun next() = when (this) {
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

class MoodChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var radius = 0.0f
    private var fanSpeed = FanSpeed.OFF
    private var moods: ArrayList<Mood> = ArrayList<Mood>()
    private val pointPosition: PointF = PointF(0.0f, 0.0f)
    private var xValue = 0f
    private var yValue = 0f
    private val blend: PorterDuff.Mode = PorterDuff.Mode.SCREEN
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        xfermode = PorterDuffXfermode(blend)
    }
    private val paint1 = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    init {
        moods?.addAll(
            listOf(
                Mood(0.0, 150f, Color.BLUE),
                Mood(1.0, 280f, Color.GREEN),
                Mood(2.0, 220f, Color.YELLOW),
                Mood(3.0, 180f, Color.parseColor("#412132")),
                Mood(4.0, 280f, Color.MAGENTA),
                Mood(5.0, 220f, Color.GRAY)
            )
        )
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true
        fanSpeed = fanSpeed.next()
        contentDescription = resources.getString(fanSpeed.label)
        invalidate()
        return true
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
    }

    private fun computeXY(angle: Double, radius: Float) {
        val angles = angle * (Math.PI / 3)
        xValue = (radius * cos(angles)).toFloat() + width / 2
        yValue = (radius * sin(angles)).toFloat() + height / 2

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var i = 0
        canvas.drawLine((width / 2).toFloat(), 0f, (width / 2).toFloat(), height.toFloat(), paint1)
        canvas.drawLine(0f, (height / 2).toFloat(), width.toFloat(), (height / 2).toFloat(), paint1)
        for (x in 1..1000) {
            if (cos(x.toDouble()) == 1.0) {
                Log.i("angeeeel", "   " + i.toString())
            }
        }

        /*

        while (i < moods.size) {
            if (moods[i].radius<100f){
                paint.color = Color.RED
            }else if (moods[i].radius>100f && moods[i].radius<200f){
                paint.color = Color.YELLOW
            }else if (moods[i].radius>200f && moods[i].radius<300f){
                paint.color = Color.GREEN
            }*/

        while (i < moods.size) {
            paint.color = moods[i].color
            computeXY(moods[i].angel, moods[i].radius)
            canvas.drawCircle(xValue, yValue, moods[i].radius, paint)
            i += 1
        }
    }
}