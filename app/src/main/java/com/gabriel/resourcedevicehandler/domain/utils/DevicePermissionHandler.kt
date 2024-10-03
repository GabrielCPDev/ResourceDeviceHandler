package com.gabriel.resourcedevicehandler.domain.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class DevicePermissionHandler(private val context: Context) : PermissionHandler {

    override fun isAllowed(permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun requestPermission(permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), requestCode)
    }
}