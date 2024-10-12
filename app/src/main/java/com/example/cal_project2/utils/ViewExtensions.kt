package com.example.cal_project2.utils

import android.view.View

object ViewExtensions {

    fun View.visible(isVisible: Boolean, nonVisibleMode: Int) {
        this.visibility =  if (isVisible) View.VISIBLE else nonVisibleMode
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.INVISIBLE
    }

    fun View.remove() {
        this.visibility = View.GONE
    }
}