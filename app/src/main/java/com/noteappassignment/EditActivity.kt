package com.noteappassignment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.noteappassignment.databinding.ActivityAddNotesBinding
import com.noteappassignment.databinding.ActivityEditBinding
import java.util.Random


class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var databaseHelper: DataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDataBase()
        initViews()
    }
    private fun initDataBase() {
        databaseHelper = DataBaseHelper(this)
    }
    private fun initViews() {
        /*putExtra("",notes.title)
        putExtra("description",notes.description)
        putExtra("note_date",notes.note_date)
        putExtra("bgColor",notes.bgColor)
        putExtra("id",notes.id)*/
        var title = intent.extras?.getString("title")
        var des = intent.extras?.getString("description")
        var bgColor = intent.extras?.getString("bgColor")
        var id = intent.extras?.getLong("id")
        with(binding){
            etTitle.setText(title)
            etDescription.setText(des)
            btnEditNote.setOnClickListener {
                val rnd = Random()
                val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                databaseHelper.update(id!!,etTitle.text.toString(), etDescription.text.toString(),bgColor!!,"12-21-2023")
                Toast.makeText(this@EditActivity,"Note Edited successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}