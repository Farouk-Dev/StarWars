package com.example.testmid.utils

import android.app.AlertDialog
import android.content.Context


object DialogUtils {
    /*show dialog function*/
    fun showDialog(context: Context, message: String, positiveBtnText: String) {

        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setPositiveButton(
                positiveBtnText) { dialog, id -> dialog.cancel() }
        builder.show()

    }


}