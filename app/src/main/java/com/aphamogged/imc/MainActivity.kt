package com.aphamogged.imc

import android.R.attr.fontWeight
import android.R.attr.onClick
import android.R.attr.text
import android.os.Bundle
import android.text.Layout
import android.util.Log.i
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlin.reflect.typeOf

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
fun CalcularImc(altura: Double, peso: Int):Double{
        var imc =  peso / (altura * altura)
        return imc
}
@Composable
fun  ImcScreen(modifier: Modifier = Modifier) {
    var altura by remember {
        mutableStateOf("")
    }
    var peso by remember{
        mutableStateOf("")
    }
    var imc by remember{
        mutableStateOf(0.0)
    }
    val imcFormatado = "%.2f".format(imc)
    var mensagem = when{
        imc == 0.0-> " ${imcFormatado}   Digite um valor maior"
        imc < 18.5-> " ${imcFormatado}   Abaixo do peso"
        imc >18.5 && imc < 25 -> " ${imcFormatado}  Peso ideal"
        imc >= 25 && imc < 30 -> "  ${imcFormatado}  Levemente acima do peso "
        imc >= 30 && imc < 35 -> "${imcFormatado}    Obesidade grau I"
        imc >= 35 && imc < 40 -> "${imcFormatado}    Obesidade grau II"
        imc >= 40 -> "${imcFormatado}    Obesidade grau II"
        else
            -> "Erro"
    }
    var corFundo = when{
        imc >18.5 && imc < 25 ->  Color(112, 238, 10, 255)
        imc >= 25 && imc < 30 -> Color(255, 152, 0, 255)
        imc >= 30 && imc < 35 -> Color(244, 67, 54, 255)
        else
            -> Color(244, 67, 54, 255)
    }

    Column(modifier.fillMaxSize()) {


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
               .offset(y= -30.dp)
               .padding(horizontal = 24.dp),
               colors = CardDefaults.cardColors(
                   containerColor = Color(0xfff9f6f6)
               ),
               elevation = CardDefaults.cardElevation(4.dp),
                ) {

               Column(
                   modifier = modifier.fillMaxSize()
                       .padding(horizontal = 24.dp),
                   Arrangement.SpaceEvenly,
                   Alignment.CenterHorizontally
               ) {

                   Text(
                       text = "Seus dados",
                       fontWeight = FontWeight.Bold,
                       fontSize = 24.sp,
                       color = colorResource(R.color.corDoApp)
                   )
                   OutlinedTextField(
                       value =altura,
                       onValueChange = { novoValor->
                           if (altura.length <=3) {
                               altura = novoValor
                           }
                       },
                       placeholder ={
                           Text("Altura")
                       },
                       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                       shape = RoundedCornerShape(10.dp)
                   )
                   OutlinedTextField(
                       value =peso,
                       onValueChange = {
                           novoValor->
                           if(peso.length <=3){
                               peso = novoValor
                           }

                       },
                       placeholder ={
                           Text("Peso")
                       },
                       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                       shape = RoundedCornerShape(10.dp)
                   )
                   Button(
                       modifier = Modifier.fillMaxWidth(),
                       onClick = {
                           var alturaN = 0.0
                           var pesoN = 0
                           if (alturaN != null && pesoN != null){

                           if (altura.contains(",")) {
                               altura = altura.replace(",", ".")
                           }
                               if (peso.contains(",")) {
                                  peso = peso.replace(",", "")
                               }
                               else if( peso.contains(".")) {
                                  peso = peso.replace(",", "")
                               }
                               if (!altura.contains(".")) {
                                   alturaN = altura.toDouble() / 10
                               }
                               else {
                                   alturaN = altura.toDouble()
                               }
                                pesoN = peso.toInt()
                                   imc =  CalcularImc(alturaN,pesoN)

                           }
                       }
                   ){
                     Text(
                         text= "Calcular"
                     )
                   }
               }


           }
            Card (
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(10.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(corFundo),
                    Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "$mensagem",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

            }
        }

    }
}