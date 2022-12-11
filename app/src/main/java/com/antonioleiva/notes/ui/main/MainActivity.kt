package com.antonioleiva.notes.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.antonioleiva.notes.App
import com.antonioleiva.notes.data.NotesRepository
import com.antonioleiva.notes.databinding.ActivityMainBinding
import com.antonioleiva.notes.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var notesRepository: NotesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notesRepository = NotesRepository((application as App).notesDb.notesDao())

        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        val notesAdapter = NotesAdapter(
            onClick = { navigateToDetail(it.id) },
            onDeleteClick = {
                lifecycleScope.launch {
                    notesRepository.delete(it)
                }
            }
        )

        binding.notesList.adapter = notesAdapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                notesRepository.getAll().collect() {
                    notesAdapter.submitList(it)
                }
            }
        }

        binding.addNoteButton.setOnClickListener {
            navigateToDetail()
        }
    }

    private fun navigateToDetail(noteId: Int = -1) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_NOTE_ID, noteId)
        }
        startActivity(intent)
    }
}