package com.gabriel.resourcedevicehandler.presentation.ui.components

import android.content.Context
import androidx.appcompat.app.AlertDialog

class PermissionDialog(
    private val context: Context,
    private val title: String,
    private val message: String,
    private val positiveButtonText: String,
    private val onPositiveClick: (() -> Unit)? = null
) {

    private val dialog: AlertDialog = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { dialog, _ ->
            onPositiveClick?.invoke()
            dialog.dismiss()
        }
        .create()

    fun show() {
        dialog.show()
    }
}