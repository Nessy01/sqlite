package com.example.sqlite


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB1 (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "hello_there"

        private val DATABASE_VERSION = 1 //jak bede nastepne wesje to bedzie sie aktualizowac

        val TABLE_NAME = "general_kenobi"

        val ID_COL = "id"

        val NAME_COl = "name"

        val AGE_COL = "age"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                AGE_COL + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }
    // checking if table already exists
    override fun onUpgrade(
        db: SQLiteDatabase,
        p1: Int,
        p2: Int) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addName(
        name : String,
        age : Int) {

        val values = ContentValues()
        values.put(NAME_COl, name) //dodawanie wartosci
        values.put(AGE_COL, age)
        val db = this.writableDatabase // otwiera/tworzy baze do odczytu i zapisu
        db.insert(TABLE_NAME, null, values) //nowy wiersz
        db.close() // zamyka bd
    }

    //
    fun getName(): Cursor? {

        val db = this.readableDatabase // Tworzy/otwiera bazę danych tylko do odczytu.

        //wykonuje zapytanie sql i zwraca obiekt cursor
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }
}