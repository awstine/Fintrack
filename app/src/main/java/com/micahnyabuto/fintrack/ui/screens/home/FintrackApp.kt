package com.micahnyabuto.fintrack.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.primaryLight
import com.example.compose.scrimLight
import com.example.compose.scrimLightMediumContrast
import com.micahnyabuto.fintrack.R


@Composable
fun FintrackApp() {
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = painterResource(R.drawable.home),
            unselectedIcon = painterResource(R.drawable.home2),
        ),

        BottomNavigationItem(
            title = "Chart",
            selectedIcon = painterResource(R.drawable.barchart2),
            unselectedIcon = painterResource(R.drawable.barchart),
        ),

        BottomNavigationItem(
            title = "Add",
            selectedIcon = painterResource(R.drawable.add),
            unselectedIcon = painterResource(R.drawable.add),
        ),

        BottomNavigationItem(
            title = "Add",
            selectedIcon = painterResource(R.drawable.wallet2),
            unselectedIcon = painterResource(R.drawable.wallet),
        ),

        BottomNavigationItem(
            title = "Profile",
            selectedIcon = painterResource(R.drawable.profile2),
            unselectedIcon = painterResource(R.drawable.profile),
        ),
    )

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth(),
                containerColor = Color.White
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) },
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it) // Use the padding provided by Scaffold
            //  .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp, top = 70.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "Good Afternoon",
                        fontSize = 20.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(Modifier.height(8.dp))

                    Text(
                        "Jaba Limpepe",
                        fontSize = 30.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                Image(
                    modifier = Modifier
                        .size(75.dp)
                        .padding(22.dp)
                        .background(primaryLight, RoundedCornerShape(5.dp)),
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Profile picture"
                )
            }

            Spacer(Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BalanceCard()
            }

            Spacer(Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Transaction History",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = scrimLight
                )
                Text(
                    "See all",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryLight
                )
            }

            Spacer(Modifier.height(12.dp))

            LazyColumn {
                items(transactions) { transaction ->
                    TransactionsCard(transaction)
                }
            }
        }
    }
}

@Composable
fun BalanceCard(
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation =CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = primaryLight,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Total Balance",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Ksh 15,000",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Income",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Ksh 15,000",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Green
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Expenses",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Ksh 15,000",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionsCard(
    transaction: Transactions
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = transaction.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = scrimLightMediumContrast
            )
            Text(
                text = transaction.date,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = transaction.amount,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = if (transaction.amount.contains("-")) Color.Red else Color.Green
        )
    }
}