package dora.widget

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.Transformation
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import dora.widget.loadingdialog.R

class DoraLoadingDialog(context: Context) : AppCompatDialog(context, R.style.DoraView_Theme_Widget_LoadingDialog) {

    private var dialogView: View? = null
    private val loadingInAnim: AnimationSet
    private val loadingOutAnim: AnimationSet
    private val overlayOutAnim: Animation
    private var tvMessage: TextView? = null
    private var messageTextSize: Float = 14f
    private var message: String = context.resources.getString(R.string.loading)
    private var closeFromCancel = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dview_loading_dialog)
        dialogView = window!!.decorView.findViewById(android.R.id.content)
        tvMessage = findViewById(R.id.tv_loading_message)
        tvMessage!!.text = message
        tvMessage!!.textSize = messageTextSize
    }

    /**
     * 显示对话框。
     */
    fun show(message: String? = "", build: (DoraLoadingDialog.() -> Unit)? = null) : DoraLoadingDialog {
        if (message != null) {
            this.message = message
        }
        build?.invoke(this)
        create()
        show()
        return this
    }

    /**
     * 隐藏对话框。
     */
    fun dismissWithAnimation() {
        dismissWithAnimation(false)
    }

    fun messageTextSize(textSize: Float) {
        this.messageTextSize = textSize
    }

    override fun onStart() {
        super.onStart()
        dialogView?.startAnimation(loadingInAnim)
    }

    override fun cancel() {
        dismissWithAnimation(true)
    }

    private fun dismissWithAnimation(fromCancel: Boolean) {
        closeFromCancel = fromCancel
        dialogView?.startAnimation(loadingOutAnim)
    }

    init {
        setCancelable(true)
        setCanceledOnTouchOutside(false)
        loadingInAnim = AnimationUtils.loadAnimation(context, R.anim.anim_dview_loading_dialog_in) as AnimationSet
        loadingOutAnim = AnimationUtils.loadAnimation(context, R.anim.anim_dview_loading_dialog_out) as AnimationSet
        loadingOutAnim.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                dialogView?.visibility = View.INVISIBLE
                dialogView?.post {
                    if (closeFromCancel) {
                        super@DoraLoadingDialog.cancel()
                    } else {
                        super@DoraLoadingDialog.dismiss()
                    }
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        overlayOutAnim = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                val wlp = window!!.attributes
                wlp.alpha = 1 - interpolatedTime
                window!!.attributes = wlp
            }
        }
        overlayOutAnim.setDuration(120)
    }
}