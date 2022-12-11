package com.antonioleiva.notes.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String
)

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDb : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}

@Dao
interface NotesDao {

    @Query("SELECT * FROM NoteEntity")
    fun getAll(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    fun getById(id: Int): Flow<NoteEntity?>

    @Insert
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)
}
