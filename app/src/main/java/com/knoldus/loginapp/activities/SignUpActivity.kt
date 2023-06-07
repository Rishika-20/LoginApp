package com.knoldus.loginapp.activities

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.knoldus.loginapp.R

class SignUpActivity : AppCompatActivity() {

     private  lateinit var textview: TextView
     private  lateinit var editText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_signup)

        val Sign_up : TextView = findViewById(R.id.SignUp)
        val user_name:EditText = findViewById(R.id.username)
        val email_id : EditText = findViewById(R.id.email)
        val linkedinUrl:EditText = findViewById(R.id.linkedin)

        val password:EditText = findViewById(R.id.password)
        val re_enter_pass :EditText = findViewById(R.id.re_password)
        val create_user:Button = findViewById(R.id.createUser)

        // Find the login button in your layout
        val loginButton:Button = findViewById(R.id.login)

        // Set a click listener for the login button
        loginButton.setOnClickListener {
            // Start the LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        create_user.setOnClickListener {
            val user = user_name.text.toString().trim()
            val email = email_id.text.toString().trim()
            val linkedin = linkedinUrl.text.toString().trim()
            val pass = password.text.toString().trim()
            val re_pass = re_enter_pass.text.toString().trim()

            pasteLinkedInUrl()

            // validation
            if (user.isEmpty()) {
                user_name.error = "Please enter your username!"
            } else if (!isNotValidUser(user)) {
                user_name.error = "Invalid user"
            } else if (email.isEmpty()) {
                email_id.error = "Email is required"
            } else if (!isValidEmail(email)) {
                email_id.error = "Invalid email"
           } else if (linkedin.isNotEmpty() && !isNotValidUser(linkedin)) {
                        linkedinUrl.error = "Invalid Username"
           }
            else if(pass.isNotEmpty() && !isValidPassword(pass)){
                password.error = "Invalid password, it must contain all the characters."
            }else if(re_pass.isNotEmpty() && !isValidPassword(re_pass)){
                re_enter_pass.error = "Invalid password,it must contain all the characters."
            } else if (re_pass!= pass){
                re_enter_pass.error = "Password didn't match."
            }
            else{
                createUser(user,email,linkedin,pass,re_pass)
            }
        }


    }
     /**
      * User validation
      **/
    private fun isNotValidUser(username: String): Boolean {
        val usernamePattern = "^[a-zA-Z0-9._-]{3,}$"
        return username.matches(usernamePattern.toRegex())
    }
    /**
     * Email validation
     **/
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }
    /**
     * Linkedin (Url) validation
     **/
    private fun isValidLinkedInUrl(linkedinUrl: String): Boolean {
        val urlPattern = "^(https?://)?([a-z]{2,3}\\.)*linkedin\\.com/.+"
        return linkedinUrl.matches(urlPattern.toRegex())
    }
    /**
     * Password validation
     **/
    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+\$).{8,}$"
        return password.matches(passwordPattern.toRegex())
    }

    private fun createUser(email: String, linkedinUrl: String, username: String,
                           pass: String,re_pass:String) {

        Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()
        // To navigate to the other activity
        val intent = Intent(this, ListOfClubs::class.java)
        startActivity(intent)
        finish()
    }

    private fun pasteLinkedInUrl() {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (clipboardManager.hasPrimaryClip() && clipboardManager.primaryClip?.itemCount ?: 0 > 0) {
            val urlItem = clipboardManager.primaryClip!!.getItemAt(0)
            val url = urlItem.text.toString().trim()

            // Do something with the pasted URL, such as updating the EditText field
           // linkedinUrl.setText(url)
        }
    }



}