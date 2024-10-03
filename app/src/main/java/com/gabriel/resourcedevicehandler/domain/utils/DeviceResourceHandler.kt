package com.gabriel.resourcedevicehandler.domain.utils

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.gabriel.resourcedevicehandler.domain.utils.DeviceRequestConstants.REQUEST_BLUETOOTH_CONNECT
import com.gabriel.resourcedevicehandler.domain.utils.DeviceRequestConstants.REQUEST_CAMERA_PERMISSION

class DeviceResourceHandler(
    private val context: Context,
    private val permissionHandler: DevicePermissionHandler
) : ResourceHandler {

    fun isAllowed(permission: String): Boolean {
        return permissionHandler.isAllowed(permission)
    }

    @SuppressLint("MissingPermission")
    override fun accessBluetooth(): Boolean {
        return try {
            val bluetoothManager: BluetoothManager = context.getSystemService(BluetoothManager::class.java)
            val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()

            if (bluetoothAdapter == null) {
                false
            } else {
                permissionHandler.requestPermission(Manifest.permission.BLUETOOTH_CONNECT, REQUEST_BLUETOOTH_CONNECT)

                if (!bluetoothAdapter.isEnabled) {
                    if (permissionHandler.isAllowed(Manifest.permission.BLUETOOTH_CONNECT)) {
                        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                        (context as AppCompatActivity).startActivityForResult(enableBtIntent, REQUEST_BLUETOOTH_CONNECT)
                        true
                    } else {
                        false
                    }
                } else {
                    true
                }
            }
        } catch (e: Exception) {
            false
        }
    }


    override fun accessCamera() {
        permissionHandler.requestPermission(Manifest.permission.CAMERA, REQUEST_CAMERA_PERMISSION)
    }
}