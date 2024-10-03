package com.gabriel.resourcedevicehandler.domain.useCase

import com.gabriel.resourcedevicehandler.domain.utils.DeviceResourceHandler
import com.gabriel.resourcedevicehandler.domain.utils.ResourceHandler

class ResourceAccessUseCase(private val resourceHandler: ResourceHandler) {

    fun accessBluetooth(): Boolean = resourceHandler.accessBluetooth()
    fun accessCamera() = resourceHandler.accessCamera()
    fun isAllowed(key: String) = (resourceHandler as DeviceResourceHandler).isAllowed(key)

}