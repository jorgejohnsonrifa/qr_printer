package com.jjriffa.scannerapp.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jjriffa.scannerapp.navegation.AppScreens
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    var scannedResult by remember { mutableStateOf("") }
    val context = LocalContext.current
    val activity = context as? Activity
    var showDialog by remember { mutableStateOf(false) }

    BackHandler {
        //activity?.finish()
        activity?.moveTaskToBack(true)
    }

    // Registro para el escaneo de QR
    val scanLauncher = rememberLauncherForActivityResult(contract = ScanContract()) { result ->
        if (result.contents != null) {
            scannedResult = result.contents
        }
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Home", color = Color.White) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF674FA4),
                titleContentColor = Color.White
            ),
            actions = {
                IconButton( onClick = { showDialog = true}) {

                    Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        tint = Color.White,
                        contentDescription = "Cerrar sesión")
                }
            }

        )
    }

    ) {

        BodyContent(scanLauncher)

    }

    // Diálogo de confirmación
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Cerrar sesión") },
            text = { Text("¿Está seguro que quiere cerrar sesión?") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    navController.navigate(AppScreens.LoginScreen.route) {
                        popUpTo(AppScreens.LoginScreen.route) { inclusive = true }
                    }
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}



@Composable
fun BodyContent(scanLauncher: androidx.activity.result.ActivityResultLauncher<ScanOptions>){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = "Escanear QR")
      Button(onClick = {
          val options = ScanOptions().apply {
              setDesiredBarcodeFormats(ScanOptions.QR_CODE)
              setPrompt("Escanea un código QR")
              setBeepEnabled(false)
              setCameraId(0) // Usa la cámara trasera
          }
          scanLauncher.launch(options)

      }) {
          Text(text = "Escanear")
      }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    val navController = rememberNavController()
    HomeScreen(navController)


}