package com.example.marketapp.features.auth.view.viewmodels.register

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class RegisterEvent {

    data class Register(var navigator: DestinationsNavigator) : RegisterEvent()
    data class OnLoginClick(var navigator: DestinationsNavigator) : RegisterEvent()
    data class OnBackClick(var navigator: DestinationsNavigator) : RegisterEvent()

}
