package com.knoldus.loginapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.AlignmentSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.knoldus.loginapp.R
import com.knoldus.loginapp.database.LoginDb

class LoginActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var DB: LoginDb

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.btnlogin)
        DB = LoginDb(this)

        login.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                Toast.makeText(this@LoginActivity, "All Field Required", Toast.LENGTH_SHORT).show()
            } else {
                val checkUserPass = DB.checkUsernamePassword(user, pass)
                if (checkUserPass) {
                    Toast.makeText(this@LoginActivity, "Login successfully", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}