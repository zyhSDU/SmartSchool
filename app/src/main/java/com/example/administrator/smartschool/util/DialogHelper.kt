package com.example.administrator.smartschool.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View

/**
 * Created by Administrator on 2018/9/22 0022.
 */

object DialogHelper {
    @SuppressLint("InflateParams")
    fun build(
            title: String,
            context: Context,
            layoutId: Int,
            init: (dialogView: View) -> ((
                    dialog: DialogInterface,
                    which: Int
            ) -> Unit)
    ): AlertDialog.Builder {
        val dialog = AlertDialog.Builder(context)
        val dialogView: View = LayoutInflater.from(context).inflate(layoutId, null)
        val initPositiveButton: (dialog: DialogInterface, which: Int) -> Unit = init(dialogView)
        dialog.setTitle(title)
        dialog.setView(dialogView)
        dialog.setPositiveButton("确定", { dialog, which ->
            initPositiveButton(dialog, which)
        })
        return dialog
    }
}
