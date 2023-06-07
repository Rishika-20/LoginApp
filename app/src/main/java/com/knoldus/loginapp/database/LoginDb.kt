package com.knoldus.loginapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LoginDb(context: Context) : SQLiteOpenHelper(context, "UserData", null, 1) {

    override fun onCreate(DB: SQLiteDatabase) {
        DB.execSQL("CREATE TABLE Userdetails(username TEXT PRIMARY KEY, password TEXT)")
    }

    override fun onUpgrade(DB: SQLiteDatabase, i: Int, i1: Int) {
        DB.execSQL("DROP TABLE IF EXISTS Userdetails")
    }

    fun insertData(username: String, password: String): Boolean {
        val DB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = DB.insert("userdetails", null, contentValues)
        return result != -1L
    }

    fun checkUsername(username: String): Boolean {
        val DB = this.writableDatabase
        val cursor = DB.rawQuery(
            "SELECT * FROM userdetails WHERE username=?",
            arrayOf(username)
        )
        return cursor.count > 0
    }

    fun checkUsernamePassword(username: String, password: String): Boolean {
        val DB = this.writableDatabase
        val cursor = DB.rawQuery(
            "SELECT * FROM userdetails WHERE username=? AND password=?",
            arrayOf(username, password)
        )
        return cursor.count > 0
    }
}