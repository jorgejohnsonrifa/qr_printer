package com.jjriffa.scannerapp.navegation

sealed class AppScreens (val route: String){
    object LoginScreen: AppScreens(route = "login_screeen")
    object HomeScreen: AppScreens(route = "home_screeen")
    object DatosMuestraScren: AppScreens(route= "datos_muestra_screeen" )

}