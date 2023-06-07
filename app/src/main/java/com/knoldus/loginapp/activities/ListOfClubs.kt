package com.knoldus.loginapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.knoldus.loginapp.R

class ListOfClubs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_clubs)

        val messageTextViewClub = findViewById<TextView>(R.id.messageTextView)
        messageTextViewClub.text = "Welcome to the list of clubs! This page will show the list of clubs. "

    }
}