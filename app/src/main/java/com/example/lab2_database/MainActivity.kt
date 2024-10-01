package com.example.lab2_database

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab2_database.ui.DisplayBusinessList
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Set the Compose content
        setContent {
            DisplayBusinessList() // Now it will resolve correctly
        }
    }
}
