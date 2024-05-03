package com.noteappassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.noteappassignment.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: DataBaseHelper
    private lateinit var listOfNotesAdapter: ListOfNotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDataBase()
        initViews()
    }
    private fun initDataBase() {
        databaseHelper = DataBaseHelper(this)
    }

    override fun onResume() {
        super.onResume()
        val data = databaseHelper.readData()
        if (data != null) {
            listOfNotesAdapter = ListOfNotesAdapter(data)
        }
        with(binding) {
            rv.layoutManager = StaggeredGridLayoutManager(2,0)
            rv.adapter = listOfNotesAdapter
            /*rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.adapter = listOfNotesAdapter*/
        }
    }

    private fun initViews() {/*
        binding.btnFetch.setOnClickListener() {
            val data = databaseHelper.readData()
            if (data != null) {
                binding.listOfUsers.adapter =
                    ArrayAdapter(this, R.layout.simple_list_item_1, data)
            }
            else{
                Toast.makeText(this,"No data Found", Toast.LENGTH_LONG).show()
            }

        }*/
        /*binding.btnSave.setOnClickListener() {
            Toast.makeText(this,"DATA SAVING", Toast.LENGTH_LONG).show()
            databaseHelper.insertData("t1", "d1","c1","12-21-2223")
        }*/
        /*binding.btnDelete.setOnClickListener() {
            databaseHelper.delete(3)
        }
        binding.btnUpdate.setOnClickListener {
            databaseHelper.update(4,"Hari", "d1","c1","12-21-2223")
        }*/
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddNotesActivity::class.java))
        }
    }
}