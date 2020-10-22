package com.tan.core.util

import android.content.Context
import android.widget.Toast

object ToastTool {
    fun show(context: Context?, message: String?) {
        if (!CommonUtil.isEmpty(message)) {
            val toast = Toast.makeText(context, "11", Toast.LENGTH_SHORT)
            toast.setText(message)
            toast.show()
        }
    }
}