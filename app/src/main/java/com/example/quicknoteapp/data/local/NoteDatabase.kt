package com.example.quicknoteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quicknoteapp.domain.model.Note


@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}