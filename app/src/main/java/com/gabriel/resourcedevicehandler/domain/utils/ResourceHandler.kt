package com.gabriel.resourcedevicehandler.domain.utils

interface ResourceHandler {
    fun accessBluetooth():Boolean
    fun accessCamera()
}