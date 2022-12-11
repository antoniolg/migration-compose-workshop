package com.antonioleiva.notes.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.antonioleiva.notes.App
import com.antonioleiva.notes.R
import com.antonioleiva.notes.data.NotesRepository
import com.antonioleiva.notes.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private val vm: DetailViewModel by viewModels {
        val repository = NotesRepository((application as App).notesDb.notesDao())
        val noteId = intent.getIntExtra(EXTRA_NOTE_ID, -1)
        DetailViewModelFactory(repository, noteId)
    }

    companion object {
        const val EXTRA_NOTE_ID = "note_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        val noteId = intent.getIntExtra(EXTRA_NOTE_ID, -1)

        if (noteId == -1) {
            supportActionBar?.title = getString(R.string.new_note)
            binding.saveButton.text = getString(R.string.add_task)
        } else {
            supportActionBar?.title = getString(R.string.edit_note)
            binding.saveButton.text = getString(R.string.update_task)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.note.collect { note ->
                    if (note != null) {
                        binding.title.setText(note.title)
                        binding.description.setText(note.description)
                    }
                }
            }
        }

        binding.saveButton.setOnClickListener {
            val title = binding.title.text.toString()
            val description = binding.description.text.toString()
            vm.save(title, description)
            finish()
        }

    }
}