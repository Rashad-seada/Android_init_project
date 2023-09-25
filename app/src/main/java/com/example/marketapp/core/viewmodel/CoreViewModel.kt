package com.example.marketapp.core.viewmodel

import androidx.lifecycle.ViewModel
import com.example.marketapp.destinations.LoginMethodsScreenDestination
import com.example.marketapp.destinations.LoginScreenDestination
import com.example.marketapp.destinations.OnBoardingScreenDestination
import com.example.marketapp.destinations.RegisterScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor() : ViewModel() {

    private fun initApp() {

    }

    suspend fun onSplashScreenLaunch(navigator : DestinationsNavigator?){
        initApp()
        delay(1000)
        navigator?.navigate(OnBoardingScreenDestination())
    }

    fun onOnBoardingScreenNextClick(navigator : DestinationsNavigator?){
        navigator?.navigate(LoginMethodsScreenDestination())
    }

    fun onOnBoardingScreenSkipClick(navigator : DestinationsNavigator?){
        navigator?.navigate(LoginMethodsScreenDestination())
    }

    fun onMethodsScreenLoginClick(navigator : DestinationsNavigator?){
        navigator?.navigate(LoginScreenDestination())
    }

    fun onMethodsScreenRegisterClick(navigator : DestinationsNavigator?){
        navigator?.navigate(RegisterScreenDestination())
    }

    fun onMethodsScreenLoginWithGoogleClick(navigator : DestinationsNavigator?){
        //navigator?.navigate(MethodsScreenDestination())
    }

    sealed class CoreUiEvent

}