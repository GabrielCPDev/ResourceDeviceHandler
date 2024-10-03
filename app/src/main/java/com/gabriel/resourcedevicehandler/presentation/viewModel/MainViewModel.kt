package com.gabriel.resourcedevicehandler.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabriel.resourcedevicehandler.domain.useCase.ResourceAccessUseCase

class MainViewModel(
    private val resourceAccessUseCase: ResourceAccessUseCase

) : ViewModel(
) {

    fun requestAccessBluetooth(): Boolean {
        return resourceAccessUseCase.accessBluetooth()
    }

    fun requestAccessCamera() {
        resourceAccessUseCase.accessCamera()
    }

    fun isAllowed(key: String): Boolean {
        return resourceAccessUseCase.isAllowed(key)
    }


    class MainViewModelFactory(
        private val resourceAccessUseCase: ResourceAccessUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(resourceAccessUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}