package com.antonioleiva.notes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.antonioleiva.notes.data.Note
import com.antonioleiva.notes.data.NotesRepository
import kotlinx.coroutines.launch

class MainViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    val notes = notesRepository.getAll()

    fun delete(note: Note) {
        viewModelScope.launch {
            notesRepository.delete(note)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val notesRepository: NotesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(notesRepository) as T
    }
}