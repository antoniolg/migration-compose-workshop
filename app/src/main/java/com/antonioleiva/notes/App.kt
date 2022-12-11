package com.antonioleiva.notes

import android.app.Application
import androidx.room.Room
import com.antonioleiva.notes.data.db.NoteEntity
import com.antonioleiva.notes.data.db.NotesDb
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class App : Application() {

    lateinit var notesDb: NotesDb
        private set

    override fun onCreate() {
        super.onCreate()

        notesDb = Room.databaseBuilder(
            this,
            NotesDb::class.java,
            "notes.db"
        ).build()

        /*GlobalScope.launch {
            val dao = notesDb.notesDao()
            dao.insert(NoteEntity(1, "Title 1", "Description"))
            dao.insert(NoteEntity(2, "Title 2", "Description"))
            dao.insert(NoteEntity(3, "Title 3", "Description"))
        }*/
    }
}