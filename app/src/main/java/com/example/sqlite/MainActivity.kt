package com.example.sqlite

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // below code is to add on click
        // listener to our add name button
        val db = DB1(this, null)
        addName.setOnClickListener{
            val name = enterName.text.toString()
            val age = enterAge.text.toString()
            db.addName(name, age)
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()
            enterName.text.clear()
            enterAge.text.clear()
        }

        printName.setOnClickListener{
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DB1.NAME_COl)) + "\n")
            Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DB1.AGE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DB1.NAME_COl)) + "\n")
                Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DB1.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}