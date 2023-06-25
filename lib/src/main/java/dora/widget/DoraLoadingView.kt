package dora.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import kotlin.jvm.JvmOverloads
import androidx.appcompat.widget.AppCompatImageView
import dora.widget.loadingdialog.R

class DoraLoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                                defStyleAttr: Int = 0) : AppCompatImageView(
        context, attrs, defStyleAttr) {
    private var rotateDegrees = 0f
    private var frameTime = 0
    private var needToUpdateView = false
    private var updateViewRunnable: Runnable? = null

    init {
        init()
    }

    private fun init() {
        setImageResource(R.drawable.ic_dview_loading_view_rotate)
        frameTime = 1000 / 12
        updateViewRunnable = object : Runnable {
            override fun run() {
                rotateDegrees += 30f
                rotateDegrees = if (rotateDegrees < 360) rotateDegrees else rotateDegrees - 360
                invalidate()
                if (needToUpdateView) {
                    postDelayed(this, frameTime.toLong())
                }
            }
        }
    }

    fun setAnimationSpeed(scale: Float) {
        frameTime = (1000 / 12 / scale).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.rotate(rotateDegrees, (width / 2).toFloat(), (height / 2).toFloat())
        super.onDraw(canvas)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        needToUpdateView = true
        post(updateViewRunnable)
    }

    override fun onDetachedFromWindow() {
        needToUpdateView = false
        super.onDetachedFromWindow()
    }
}