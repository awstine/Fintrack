
package com.micahnyabuto.fintrack.ui.screens.expense

import android.R.attr.enabled
import android.R.attr.navigationIcon
import android.R.attr.type
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.primaryLight
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.jar.Attributes.Name

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold (
        topBar = {
        CenterAlignedTopAppBar(
            navigationIcon= {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Localized description"
                    )
                }
            },
            title = {
                Text("Add Expense",

                    )
            },
            actions = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = "Localized description"
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = primaryLight,
                titleContentColor = White,
                navigationIconContentColor = White,
                actionIconContentColor = White

            ),
            scrollBehavior = scrollBehavior
        )
},


    ){
        EntryForm()
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryForm(
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 100.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedDate = remember { mutableStateOf(LocalDate.now()) }
         val showDatePicker = remember { mutableStateOf(false) }
        val dateFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy")

        val options = listOf("Netflix", "Spotify", "Youtube", "Twitter","")
        
        var expanded by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf(options[0]) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {  Row {
            Column(
                modifier = Modifier.padding(25.dp)

            ) {
                Text("NAME")
                Spacer(Modifier.size(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    readOnly = true,
                    value = selectedOption,
                    onValueChange = {},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
            }
        }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    )
                }
            }
        }
        Row {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(25.dp)
        ) {
        Text("AMOUNT")
            Spacer(Modifier.size(5.dp))
        OutlinedTextField(
            value= "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()

            
        )
        }
        }


        Row {
            Column(
                modifier = Modifier.padding(25.dp)
            ) {
                Text("DATE")
                Spacer(Modifier.size(5.dp))
                OutlinedTextField(
                    value= selectedDate.value.format(dateFormatter),
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker.value = true }) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = "Select date"
                            )
                        }
                    },
                    

            )
            }
        }
       // Spacer(Modifier.size(25.dp))

        Button(onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryLight,
                contentColor = White
            )
        ){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add expense"
            )
            Text("ADD EXPENSE")

        }



    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddExpenseScreenPreview(){
    AddExpenseScreen()
}

