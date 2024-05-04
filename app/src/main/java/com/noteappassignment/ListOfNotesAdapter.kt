package com.noteappassignment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.noteappassignment.databinding.EditBottomSheetBinding
import com.noteappassignment.databinding.ItemNotesBinding
import java.util.Random

class ListOfNotesAdapter(private val notesList: List<Notes>) :
    RecyclerView.Adapter<ListOfNotesAdapter.NotesViewHolder>() {
    private lateinit var binding: ItemNotesBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListOfNotesAdapter.NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemNotesBinding.inflate(layoutInflater, parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListOfNotesAdapter.NotesViewHolder, position: Int) {
        val data = notesList[position]
        holder.bindData(notesList[position], position)
    }

    override fun getItemCount(): Int = notesList.size

    inner class NotesViewHolder(private val localBinding: ItemNotesBinding) :
        RecyclerView.ViewHolder(localBinding.root) {
        fun bindData(notes: Notes, position: Int) {
            with(localBinding) {
                tvTitle.text =  notes.title
                tvDescription.text = notes.description
                tvDate.text = notes.note_date
                card.setCardBackgroundColor(notes.bgColor.toInt())
                ivEdit.setOnClickListener {
                    createBottomSheetUI(ivEdit.context,notes.title,notes.description,notes.note_date,notes.bgColor,notes.id)
                }
            }
        }
        private lateinit var databaseHelper: DataBaseHelper
        private fun createBottomSheetUI(context: Context,title: String,description: String,note_date: String,bgColor: String,id: Long ) {
            val layoutInflater = LayoutInflater.from(context)
            val dialog = BottomSheetDialog(context)
            val bottomSheetDialogBinding = EditBottomSheetBinding.inflate(layoutInflater)
            dialog.setContentView(bottomSheetDialogBinding.root)
            dialog.show()
            with(bottomSheetDialogBinding){
                btnCancel.setOnClickListener {
                    dialog.dismiss()
                }
                etTitle.setText(title)
                etDescription.setText(description)
                btnEditNote.setOnClickListener {
                    val rnd = Random()
                    databaseHelper = DataBaseHelper(context)
                    val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                    databaseHelper.update(id!!,etTitle.text.toString(), etDescription.text.toString(),bgColor!!,"12-21-2023")
                    Toast.makeText(context,"Note Edited successfully", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        }
    }
}