package com.mobileapp.habittracker.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.mobileapp.habittracker.R
import com.mobileapp.habittracker.models.ChartDataModel

class ChartView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    var chartData: List<ChartDataModel> = ArrayList()
        set(value) {
            field = value
            entryAnimationValue = 0f
        }

    private val paint = Paint()
    private val textPaint = TextPaint()
    private val legendPaint = Paint()

    var entryAnimationOn = true
    var entryAnimationSpeed = 0.02f
    private var entryAnimationValue = 0f
    private val entryValueAnimator: ValueAnimator

    init {
        context.withStyledAttributes(attributeSet, R.styleable.ChartView) {
            textPaint.textSize = getDimension(R.styleable.ChartView_textSize, 18f)
            textPaint.color = getColor(R.styleable.ChartView_textColor, Color.BLACK)
            entryAnimationOn = getBoolean(R.styleable.ChartView_entryAnimationOn, entryAnimationOn)
            entryAnimationSpeed = getFloat(R.styleable.ChartView_entryAnimationSpeed, entryAnimationSpeed)
        }

        paint.isAntiAlias = true
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.CENTER
        legendPaint.isAntiAlias = true

        entryValueAnimator = ValueAnimator.ofFloat(0f, 1f)
        entryValueAnimator.duration = 1000 // Animation duration in ms
        entryValueAnimator.addUpdateListener {
            if (entryAnimationOn) {
                entryAnimationValue = it.animatedValue as Float
                invalidate()
            }
        }
        entryValueAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (chartData.isEmpty()) return

        val totalValue = chartData.sumOf { it.value.toDouble() }.toFloat()
        if (totalValue == 0f) return

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (width.coerceAtMost(height) / 2f) * 0.6f

        val rect = RectF(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )

        var startAngle = -90f // Start from the top
        val weekDayColors = listOf(
            Color.RED,       // Chủ nhật
            Color.argb(255,255,128,255),      // Thứ hai
            Color.GREEN,     // Thứ ba
            Color.YELLOW,    // Thứ tư
            Color.CYAN,      // Thứ năm
            Color.MAGENTA,   // Thứ sáu
            Color.LTGRAY     // Thứ bảy
        )

        // Draw pie chart
        for ((index, data) in chartData.withIndex()) {
            val sweepAngle = (data.value / totalValue) * 360f * entryAnimationValue

            // Draw pie slice
            paint.color = weekDayColors[index % weekDayColors.size]
            canvas.drawArc(rect, startAngle, sweepAngle, true, paint)

            // Draw value on the slice
            val angle = Math.toRadians((startAngle + sweepAngle / 2).toDouble())
            val valueX = (centerX + radius * 0.5f * Math.cos(angle)).toFloat()
            val valueY = (centerY + radius * 0.5f * Math.sin(angle)).toFloat()
            canvas.drawText(data.value.toString(), valueX, valueY, textPaint)

            startAngle += sweepAngle
        }

        // Draw legend
        val legendStartX = centerX + radius + 20
        val legendStartY = centerY - radius
        val legendItemHeight = 40f

        for ((index, color) in weekDayColors.withIndex()) {
            // Draw color box
            legendPaint.color = color
            val top = legendStartY + index * legendItemHeight
            canvas.drawRect(
                legendStartX,
                top,
                legendStartX + 30,
                top + 30,
                legendPaint
            )

            // Draw text next to the color box
            val label = when (index) {
                0 -> "Monday"
                1 -> "Tuesday"
                2 -> "Wednesday"
                3 -> "Thusday"
                4 -> "Friday"
                5 -> "Saturday"
                6 -> "Sunday"
                else -> "N/A"
            }
            canvas.drawText(label, legendStartX + 130, top + 25, textPaint)
        }
    }
}

// ChartDataModel to hold chart data
data class ChartDataModel(
    val label: String,
    val value: Float,
    val color: Int
)
