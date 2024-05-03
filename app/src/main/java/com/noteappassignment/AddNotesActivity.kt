package com.noteappassignment

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.noteappassignment.databinding.ActivityAddNotesBinding
import java.util.Random


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
                val rnd = Random()
                val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                databaseHelper.insertData(etTitle.text.toString(), etDescription.text.toString(),"$color","12-21-2023")
                Toast.makeText(this@AddNotesActivity,"Note added successfully",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}