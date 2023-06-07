package com.knoldus.loginapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import com.knoldus.loginapp.R

class SignUpAdapter(context: Context, resource: Int,
                    objects: List<String>) :
                    ArrayAdapter<String>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?,
                         parent: ViewGroup): View {

        var convertView = convertView

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                           inflate(R.layout.item_login, parent, false)
        }

        val loginButton = convertView?.findViewById<Button>(R.id.login)
        val username = getItem(position)

        loginButton?.text = username

        // Add any necessary logic or event handling for the login button

        return convertView!!
    }
}
