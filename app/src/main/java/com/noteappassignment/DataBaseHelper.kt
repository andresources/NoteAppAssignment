package com.noteappassignment

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.noteappassignment.Notes


class DataBaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DataBaseConstants.DATABASE_NAME,
    null,
    DataBaseConstants.DATABASE_VERSION
) {


    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
                  CREATE TABLE ${DataBaseConstants.TABLE_NAME} (
                         ${DataBaseConstants.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                         ${DataBaseConstants.COLUMN_TITLE} TEXT,
                         ${DataBaseConstants.COLUMN_DESCRIPTION} TEXT,
                         ${DataBaseConstants.COLUMN_BG_COLOR} TEXT,
                         ${DataBaseConstants.COLUMN_NOTE_DATE} TEXT
)
        """.trimMargin()

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun readData(): List<Notes> {
        val datalist = mutableListOf<Notes>()

        val curser: Cursor = readableDatabase.query(
            DataBaseConstants.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
        )

        with(curser) {
            while (moveToNext()) {
                var id = getLong(getColumnIndexOrThrow(DataBaseConstants.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(DataBaseConstants.COLUMN_TITLE))
                val description = getString(getColumnIndexOrThrow(DataBaseConstants.COLUMN_DESCRIPTION))
                val bg_color = getString(getColumnIndexOrThrow(DataBaseConstants.COLUMN_BG_COLOR))
                val note_date = getString(getColumnIndexOrThrow(DataBaseConstants.COLUMN_NOTE_DATE))
                datalist.add(Notes(id, title, description,bg_color,note_date))
            }

        }
        return datalist
    }


    fun insertData(title: String, description: String,bg_color: String,note_date: String): Long {

        val values = ContentValues().apply {
            put(DataBaseConstants.COLUMN_TITLE, title)
            put(DataBaseConstants.COLUMN_DESCRIPTION, description)
            put(DataBaseConstants.COLUMN_BG_COLOR, bg_color)
            put(DataBaseConstants.COLUMN_NOTE_DATE, note_date)
        }

        return writableDatabase.insert(DataBaseConstants.TABLE_NAME, null, values)
    }

    fun delete(id: Long) : Int{
        val selection = "${DataBaseConstants.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        return writableDatabase.delete(DataBaseConstants.TABLE_NAME,selection,selectionArgs)
    }
    fun update(id: Long,title: String, description: String,bg_color: String,note_date: String) : Int{
        val values = ContentValues().apply {
            put(DataBaseConstants.COLUMN_TITLE, title)
            put(DataBaseConstants.COLUMN_DESCRIPTION, description)
            put(DataBaseConstants.COLUMN_BG_COLOR, bg_color)
            put(DataBaseConstants.COLUMN_NOTE_DATE, note_date)

        }
        val selection = "${DataBaseConstants.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        return writableDatabase.update(DataBaseConstants.TABLE_NAME,values, selection, selectionArgs)
    }
}