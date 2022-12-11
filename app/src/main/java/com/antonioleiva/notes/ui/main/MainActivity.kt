package com.antonioleiva.notes.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.antonioleiva.notes.App
import com.antonioleiva.notes.data.NotesRepository
import com.antonioleiva.notes.databinding.ActivityMainBinding
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
            onClick = { },
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
    }
}