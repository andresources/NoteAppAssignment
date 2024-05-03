package com.notesapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.noteappassignment.databinding.ActivityAddNotesBinding
import com.sqllitedemo.DataBaseHelper

class AddNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var databaseHelper: DataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDataBase()
        initViews()
    }
    private fun initDataBase() {
        databaseHelper = DataBaseHelper(this)
    }
    private fun initViews() {
        with(binding){
            btnAddNote.setOnClickListener {
                databaseHelper.insertData(etTitle.text.toString(), etDescription.text.toString(),"c1","12-21-2023")
            }
        }
    }
}