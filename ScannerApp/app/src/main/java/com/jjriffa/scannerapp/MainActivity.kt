package com.jjriffa.scannerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.jjriffa.scannerapp.navegation.AppNavigation
import com.jjriffa.scannerapp.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false) // Ajustar para Edge-to-Edge

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.systemBarsPadding()) {
                    AppNavigation()
                }
           }
        }

    }
}
@Composable
fun MyComponets(){

    Column(modifier = Modifier.padding(start = 8.dp)) {
        //MyImage()
        MyTex(text = "Hola LAICA!")
        Spacer(modifier = Modifier.height(16.dp))
        MyTex(text = "preparados")
    }
}

@Composable
fun MyTex(text: String){
    Text(text)
}

@Composable
fun MyImage(){
    Image(
        painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "imagen de prueba",
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Gray)
            .size(64.dp)
    )
}

@Composable
fun MyComponentRow(){
    Row(modifier = Modifier.padding(start = 8.dp)) {
        MyImage()
        MyComponets()
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewComponent(){
   MyComponentRow()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
  AppNavigation()
}



