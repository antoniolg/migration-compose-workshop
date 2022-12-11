package com.antonioleiva.notes.data

import com.antonioleiva.notes.data.db.NoteEntity
import com.antonioleiva.notes.data.db.NotesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepository(private val notesDao: NotesDao) {

    fun getAll(): Flow<List<Note>> = notesDao.getAll().map { it.map { it.toNote() } }

    fun getById(id: Int): Flow<Note?> = notesDao.getById(id).map { it?.toNote() }

    suspend fun insert(note: Note) = notesDao.insert(note.toEntity())

    suspend fun update(note: Note) = notesDao.update(note.toEntity())

    suspend fun delete(note: Note) = notesDao.delete(note.toEntity())
}

private fun Note.toEntity(): NoteEntity = NoteEntity(id, title, description)

private fun NoteEntity.toNote(): Note = Note(id, title, description)