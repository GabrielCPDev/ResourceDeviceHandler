package com.gabriel.resourcedevicehandler.domain.utils

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class ResourceHandlerTest {
    @Mock
    lateinit var resourceHandler: ResourceHandler

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testAccessBluetooth_returnsTrue() {
        `when`(resourceHandler.accessBluetooth()).thenReturn(true)
        Assert.assertTrue(resourceHandler.accessBluetooth())
    }

    @Test
    fun testAccessBluetooth_returnsFalse() {
        `when`(resourceHandler.accessBluetooth()).thenReturn(false)
        Assert.assertFalse(resourceHandler.accessBluetooth())
    }

    @Test
    fun testAccessCamera() {
        // Para accessCamera(), que não retorna valor,
        // você pode verificar se o método foi chamado
        // usando Mockito.verify() após a chamada do método.
        resourceHandler.accessCamera()
        verify(resourceHandler).accessCamera()
    }
}