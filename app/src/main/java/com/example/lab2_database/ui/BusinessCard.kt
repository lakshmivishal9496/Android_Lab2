package com.example.lab2_database.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab2_database.Company

@Composable
fun BusinessCard(company: Company) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = company.title)    // Display the company title
            Text(text = company.city)     // Display the company city
            Text(text = company.webpage)  // Display the company webpage
        }
    }
}
