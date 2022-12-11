package com.antonioleiva.notes.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.antonioleiva.notes.data.Note
import com.antonioleiva.notes.data.NotesRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val notesRepository: NotesRepository,
    private val id: Int
) : ViewModel() {

    val note = notesRepository.getById(id)

    fun save(title: String, description: String) {
        viewModelScope.launch {
            if (id == -1) {
                notesRepository.insert(Note(0, title, description))
            } else {
                notesRepository.update(Note(id, title, description))
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val notesRepository: NotesRepository,
    private val id: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(notesRepository, id) as T
    }
}