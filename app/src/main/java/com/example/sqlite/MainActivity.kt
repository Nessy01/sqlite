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
            cursor!!.moveToFirst()
            Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DB1.NAME_COL)) + "\n")
            Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DB1.AGE_COL)) + "\n")

            while(cursor.moveToNext()){
                Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DB1.NAME_COL)) + "\n")
                Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DB1.AGE_COL)) + "\n")
            }
            cursor.close()
        }
    }
}