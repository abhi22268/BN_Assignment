package com.example.bn_android_assignment.util

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import android.view.View.GONE
import android.widget.Toast


val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return (if (tag.length <= 16) tag else tag.substring(0, 16)) + "-Eureka"
    }


fun View.hide() {
   visibility = GONE
}

fun View.show(show: Boolean = true) {
    this.visibility = if (show) View.VISIBLE else GONE
}

fun View.changeBackgroundColor(color: Int) {
    val drawable = (background as? GradientDrawable?)?.mutate()
    (drawable as? GradientDrawable?)?.setColor(color)
}

fun Int.toPx(): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, toFloat(), Resources.getSystem().displayMetrics).toInt()
}

fun Context.showToast(str: String?) {
    str?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
}