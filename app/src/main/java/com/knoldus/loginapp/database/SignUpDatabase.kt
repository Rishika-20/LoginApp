package com.knoldus.loginapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SignupDatabase(context: Context) : SQLiteOpenHelper(context,
                         DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "signup.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_RETYPE_PASSWORD = "retypepassword"
        private const val COLUMN_LINKEDIN_URL = "linkedinurl"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_USERNAME TEXT, $COLUMN_EMAIL TEXT, $COLUMN_PASSWORD TEXT, " +
                    "$COLUMN_RETYPE_PASSWORD TEXT, $COLUMN_LINKEDIN_URL TEXT)"

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(
        username: String,
        email: String,
        password: String,
        retypePassword: String,
        linkedinUrl: String
    ): Long {
        val values = ContentValues()
        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_LINKEDIN_URL, linkedinUrl)
        values.put(COLUMN_PASSWORD, password)
        values.put(COLUMN_RETYPE_PASSWORD, retypePassword)
        val db = this.writableDatabase
        val id = db.insert(TABLE_NAME, null, values)
        db.close()

        return id
    }
}
