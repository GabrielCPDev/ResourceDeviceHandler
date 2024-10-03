package com.gabriel.resourcedevicehandler.domain.utils

interface PermissionHandler {

    fun isAllowed(permission: String): Boolean

    fun requestPermission(permission: String, requestCode: Int)
}