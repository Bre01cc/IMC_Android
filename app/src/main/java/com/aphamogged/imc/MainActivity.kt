package com.aphamogged.imc

import android.R.attr.fontWeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aphamogged.imc.ui.theme.IMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                ImcScreen(modifier = Modifier.padding((innerPadding)))
                }
            }
        }
    }
}

@Composable
fun  ImcScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {

        var
        Column(modifier = Modifier.fillMaxWidth()
            .height(160.dp)
            .background(color = colorResource(R.color.corDoApp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "IMC_Icon",
                modifier = Modifier.size(80.dp)
                    .padding(16.dp)
            )
            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Column(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 24.dp),

        ) {
           Card(modifier = Modifier.fillMaxWidth()
               .height(300.dp)
               .offset(y= -30.dp),
               colors = CardDefaults.cardColors(
                   containerColor = Color(0xfff9f6f6)
               ),
               elevation = CardDefaults.cardElevation(4.dp),
                ) {
               Text(
                   text = "Seus dados",
                   fontWeight = FontWeight.Bold,
                   fontSize = 24.sp,
                   color = Color(R.color.corDoApp)
               )
               TextField(
                   value ="",
                   onValueChange = {novoValor ->


                   },
                   placeholder ="jjj"
               ){
               }
               TextField(){

               }
           }
        }
        // -- Card formulario
        Row() {

        }
    }
}