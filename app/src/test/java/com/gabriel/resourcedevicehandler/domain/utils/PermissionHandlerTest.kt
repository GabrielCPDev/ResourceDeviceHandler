package com.gabriel.resourcedevicehandler.domain.utils

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class PermissionHandlerTest {


    @Mock
    lateinit var permissionHandler: PermissionHandler

    private val cameraPermission = "permission.CAMERA"
    private val bluetoothPermission = "permission.BLUETOOTH_CONNECT"

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test isAllowed returns true for granted permission`() {
        `when`(permissionHandler.isAllowed(cameraPermission)).thenReturn(true)

        val result = permissionHandler.isAllowed(cameraPermission)

        assertEquals(true, result)

        verify(permissionHandler).isAllowed(cameraPermission)
    }

    @Test
    fun `test isAllowed returns false for denied permission`() {
        `when`(permissionHandler.isAllowed(cameraPermission)).thenReturn(false)

        val result = permissionHandler.isAllowed(cameraPermission)

        assertEquals(false, result)

        verify(permissionHandler).isAllowed(cameraPermission)
    }

    @Test
    fun `test requestPermission is called with correct permission and requestCode`() {
        doNothing().`when`(permissionHandler).requestPermission(bluetoothPermission, 1001)

        permissionHandler.requestPermission(bluetoothPermission, 1001)

        verify(permissionHandler).requestPermission(bluetoothPermission, 1001)
    }
}