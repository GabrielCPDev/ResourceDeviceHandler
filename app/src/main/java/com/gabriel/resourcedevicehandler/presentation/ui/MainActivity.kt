package com.gabriel.resourcedevicehandler.presentation.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gabriel.resourcedevicehandler.R
import com.gabriel.resourcedevicehandler.databinding.ActivityMainBinding
import com.gabriel.resourcedevicehandler.domain.useCase.ResourceAccessUseCase
import com.gabriel.resourcedevicehandler.domain.utils.DevicePermissionHandler
import com.gabriel.resourcedevicehandler.domain.utils.DeviceRequestConstants.REQUEST_CAMERA_PERMISSION
import com.gabriel.resourcedevicehandler.domain.utils.DeviceResourceHandler
import com.gabriel.resourcedevicehandler.presentation.ui.components.PermissionDialog
import com.gabriel.resourcedevicehandler.presentation.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    val TAG =  "MainActivity"

        private lateinit var binding: ActivityMainBinding

    private val permissionHandler = DevicePermissionHandler(this)
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(
                ResourceAccessUseCase(DeviceResourceHandler(this, permissionHandler))
            )
        )[MainViewModel::class.java]

        setContentView(binding.root)

        bindingViews()
    }

    private fun bindingViews() {
        setBtnBluetoothConfig()
        setBtnCameraConfig()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CAMERA_PERMISSION -> openCamera()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun setBtnBluetoothConfig() {
        binding.btnEnableBluetooth.setOnClickListener {
            enableBluetooth()
        }
    }

    private fun setBtnCameraConfig() {
        binding.btnAccessCamera.setOnClickListener {
            openCamera()
        }
    }


    private fun enableBluetooth() {
        val isAllowedBluetoothConnect = viewModel.isAllowed(Manifest.permission.BLUETOOTH_CONNECT)

        if (!isAllowedBluetoothConnect) {
            showPermissionDeniedDialog(
                getString(R.string.title_permition_dialog),
                getString(R.string.bluetooth_message_permission_dialog)
            ) {
                val result = viewModel.requestAccessBluetooth()
                if (!result) {
                    Toast.makeText(this, getString(R.string.msg_bluetooth_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openCamera() {
        val  isAllowed = viewModel.isAllowed(Manifest.permission.CAMERA)

        if (isAllowed) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, REQUEST_CAMERA_PERMISSION)
            }
        } else {
            handleCameraAccess()
        }

    }

    private fun handleCameraAccess() {
        showPermissionDeniedDialog(
            getString(R.string.title_permition_dialog),
            getString(R.string.camera_message_permission_dialog)
        ) {
            viewModel.requestAccessCamera()
        }
    }

    private fun showPermissionDeniedDialog(title: String, message: String, action: () -> Unit) {
        val permissionDialog = PermissionDialog(
            this,
            title,
            message,
            getString(R.string.btn_dialog_agree)
        ) {
            action()
        }
        permissionDialog.show()
    }
}

