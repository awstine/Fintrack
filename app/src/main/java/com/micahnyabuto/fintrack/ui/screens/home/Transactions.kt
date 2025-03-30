package com.micahnyabuto.fintrack.ui.screens.home

data class Transactions(
    val title: String,
    val date: String,
    val amount: String,
)

val transactions = listOf(
    Transactions("Upwork","Yesterday","Ksh 15,000"),
    Transactions("Youtube","Yesterday","Ksh -5,000"),
    Transactions("Sportify","Feb 20, 2025","Ksh 2,000"),
    Transactions("Paypal","Jan 16, 2025","Ksh -150,000"),
)