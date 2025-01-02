package com.example.quicknoteapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<Note>> //Flow for more reactive data observation

    @Delete
    suspend fun delete(note: Note)

}