package com.noteappassignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noteappassignment.databinding.ItemNotesBinding

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
                tvTitle.text = notes.title
                tvDescription.text = notes.description
                tvDate.text = notes.note_date
            }
        }
    }
}