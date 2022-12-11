package com.antonioleiva.notes.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antonioleiva.notes.R
import com.antonioleiva.notes.data.Note
import com.antonioleiva.notes.databinding.NoteItemBinding

class NotesAdapter(
    private val onClick: ((Note) -> Unit),
    private val onDeleteClick: ((Note) -> Unit)
) :
    ListAdapter<Note, NotesAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view, onClick, onDeleteClick)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

    class NoteViewHolder(
        itemView: View,
        private val onClick: (Note) -> Unit,
        private val onDeleteClick: (Note) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = NoteItemBinding.bind(itemView)

        fun bind(note: Note) {
            binding.noteTitle.text = note.title
            binding.deleteButton.setOnClickListener { onDeleteClick(note) }
            binding.root.setOnClickListener { onClick(note) }
        }
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}