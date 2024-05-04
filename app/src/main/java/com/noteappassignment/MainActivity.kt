package com.noteappassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    var data: List<Notes> = ArrayList<Notes>()

    override fun onResume() {
        super.onResume()
        loadData()
    }
    private fun loadData(){
        data = databaseHelper.readData()
        if (data != null) {
            listOfNotesAdapter = ListOfNotesAdapter(data)
        }
        with(binding) {
            /*rv.layoutManager = StaggeredGridLayoutManager(2,0)
            rv.adapter = listOfNotesAdapter*/
            rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.adapter = listOfNotesAdapter
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                if(position>=0) {
                    databaseHelper.delete(data.get(position).id)
                    data = databaseHelper.readData()
                    if (data != null) {
                        listOfNotesAdapter = ListOfNotesAdapter(data)
                    }
                    binding.rv.adapter = listOfNotesAdapter
                    Toast.makeText(this@MainActivity, "Delete", Toast.LENGTH_SHORT).show()
                }
            }

        }).attachToRecyclerView(binding.rv)
    }

    private fun initViews() {
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddNotesActivity::class.java))
        }
        binding.btnRefresh.setOnClickListener {
            loadData()
        }
    }
}