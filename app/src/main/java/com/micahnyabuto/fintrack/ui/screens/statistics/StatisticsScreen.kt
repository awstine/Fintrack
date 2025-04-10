package com.micahnyabuto.fintrack.ui.screens.statistics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micahnyabuto.fintrack.R
import com.micahnyabuto.fintrack.data.Days
import com.micahnyabuto.fintrack.data.days
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Suppress("ktlint:standard:function-naming")
@Composable
fun StatisticsScreen() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        // Header Row
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, top = 70.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = "Back",
                modifier =
                    Modifier
                        .size(24.dp),
            )

            Text(
                text = "Statistics",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Image(
                painter = painterResource(R.drawable.downloadstatement),
                contentDescription = "Download statement",
                modifier = Modifier.size(24.dp),
            )
        }

        // Scrollable Days Cards
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(days) { day ->
                StatisticsDayCard(day = day)
            }
        }
        ExpenseGraph(
            expenseData = sampleData,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
        )

        TopSpendingScreen()
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun StatisticsDayCard(day: Days) {
    Card(
        modifier =
            Modifier
                .padding(4.dp)
                .width(110.dp)
                .height(60.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
            // Makes the card square
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = day.day,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ExpenseGraph(
    expenseData: Map<String, Float>,
    modifier: Modifier = Modifier,
    expenseTypes: List<String> = listOf("All", "Food", "Transport", "Entertainment"),
    onExpenseTypeSelected: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedExpenseType by remember { mutableStateOf("All") }

    Column(modifier = modifier.padding(16.dp)) {
        // Top row with price and dropdown
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Price on the left
            Text(
                text = "$${expenseData.values.maxOrNull()?.toInt() ?: 0}",
                style = MaterialTheme.typography.displaySmall,
            )

            // Dropdown on the right
            Box {
                OutlinedButton(
                    onClick = { expanded = true },
                    modifier = Modifier.width(120.dp),
                ) {
                    Text(
                        text = selectedExpenseType,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f),
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    expenseTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                selectedExpenseType = type
                                onExpenseTypeSelected(type)
                                expanded = false
                            },
                        )
                    }
                }
            }
        }
        // Graph
        Canvas(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(150.dp),
        ) {
            val maxHeight = size.height * 0.8f
            val barWidth = size.width / expenseData.size * 0.7f
            val spaceBetween = size.width / expenseData.size * 0.3f

            // Draw bars
            expenseData.values.forEachIndexed { index, value ->
                val barHeight = (value / expenseData.values.maxOrNull()!!) * maxHeight
                drawRect(
                    color = Color.Blue,
                    topLeft =
                        Offset(
                            x = index * (barWidth + spaceBetween),
                            y = size.height - barHeight,
                        ),
                    size = Size(barWidth, barHeight),
                )
            }

            // Draw month labels
            expenseData.keys.forEachIndexed { index, month ->
                drawContext.canvas.nativeCanvas.drawText(
                    month,
                    index * (barWidth + spaceBetween) + barWidth / 2,
                    size.height,
                    android.graphics.Paint().apply {
                        textSize = 12.sp.toPx()
                        color = android.graphics.Color.BLACK
                        textAlign = android.graphics.Paint.Align.CENTER
                    },
                )
            }
        }
    }
}

val sampleData =
    mapOf(
        "Mar" to 800f,
        "Apr" to 1200f,
        "May" to 600f,
        "Jun" to 1230f,
        "Jul" to 900f,
        "Aug" to 700f,
        "Sep" to 1000f,
    )

@Suppress("ktlint:standard:function-naming")
@Composable
fun TopSpendingScreen() {
    var selectedIndex by remember { mutableStateOf(-1) }
    val transactions =
        listOf(
            Transaction("Starbucks", parseDate("Jan 12, 2022"), 150.00),
            Transaction("Transfer", getYesterdayDate(), 85.00),
            Transaction("Youtube", parseDate("Jan 16, 2022"), 11.99),
            Transaction("Amazon", parseDate("Feb 5, 2022"), 75.50),
            Transaction("Netflix", parseDate("Feb 10, 2022"), 14.99),
            Transaction("Uber", parseDate("Feb 15, 2022"), 32.75),
        )

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        Text(
            text = "Top Spending",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(transactions.size) { index ->
                TransactionItem(
                    transaction = transactions[index],
                    isSelected = selectedIndex == index,
                    onSelected = { selectedIndex = index },
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun TransactionItem(
    transaction: Transaction,
    isSelected: Boolean,
    onSelected: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onSelected() },
        colors =
            CardDefaults.cardColors(
                containerColor =
                    if (isSelected) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    },
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = transaction.name,
                    style =
                        MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        ),
                )
                Text(
                    text = "- $%.2f".format(transaction.amount),
                    style =
                        MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        ),
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = transaction.date,
                style =
                    MaterialTheme.typography.bodyMedium.copy(
                        color =
                            if (isSelected) {
                                MaterialTheme.colorScheme.onPrimaryContainer
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                    ),
            )
        }
    }
}

data class Transaction(
    val name: String,
    val date: String,
    val amount: Double,
)

fun parseDate(input: String): String =
    try {
        val inputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        val date = inputFormat.parse(input)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        input
    }

fun getYesterdayDate(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, -1)
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
    return dateFormat.format(calendar.time)
}
