package com.micahnyabuto.fintrack.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.onPrimaryLight
import com.example.compose.primaryLight
import com.micahnyabuto.fintrack.R


@Composable
fun OnboardingScreen(
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
            color = primaryLight,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Save smarter save more ",
            color = primaryLight,
            fontSize = 20.sp
            // modifier = Modifier.padding(10.dp),
        )
        Spacer(modifier = Modifier.size( 50.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp)
                .height(50.dp),
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryLight,
                contentColor = onPrimaryLight
            )


        ) {
            Text(text = "Get Started",
                color = Color.White)
        }
        Row {
            Text(text = "Already have an account?")
            Spacer(modifier = Modifier.size(4.dp))
                Text(text = "Log in",
                    color= primaryLight,
                    modifier = Modifier
                        .clickable{}
                )

        }
    }

}
@Preview
@Composable
fun LandingScreenPreview(){
    OnboardingScreen()
}