package com.micahnyabuto.fintrack.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micahnyabuto.fintrack.R


@Composable
fun LandingScreen(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.fin),
            contentDescription = "Logo",
            Modifier.size(280.dp)
                .background(Color.Transparent)

        )


        Spacer(modifier = Modifier.size(30.dp))
        Text(text = "Welcome to Fintrack ",
            //modifier = Modifier.padding(10.dp),
            color = Color.Green,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Where your financial discipline begins ",
            color = Color.Green
            // modifier = Modifier.padding(10.dp),
        )
        Spacer(modifier = Modifier.size( 50.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp)
                .height(50.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.Unspecified
            )

        ) {
            Text(text = "Get Started",
                color = Color.White)
        }
    }
}
@Preview
@Composable
fun LandingScreenPreview(){
    LandingScreen()
}