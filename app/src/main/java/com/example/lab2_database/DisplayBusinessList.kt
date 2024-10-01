package com.example.lab2_database.ui

import BusinessDataSource
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab2_database.ui.BusinessCard
import com.example.lab2_database.Company
import kotlinx.coroutines.launch
@Composable
fun DisplayBusinessList() {
    val businessDataSource = remember { BusinessDataSource() }
    val businessList by businessDataSource.businessFlow.collectAsState(emptyList())

    LaunchedEffect(Unit) {
        businessDataSource.retrieveBusinesses()
    }

    // Log the business list size when data changes
    Log.d("DisplayBusinessList", "Business list size in UI: ${businessList.size}")

    if (businessList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(businessList) { company ->
                Log.d("DisplayBusinessList", "Displaying company: ${company.title}")  // Log each company displayed
                BusinessCard(company = company)
            }
        }
    }
}
