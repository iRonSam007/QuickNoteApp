package com.example.quicknoteapp.data.repository

import com.example.quicknoteapp.data.local.NoteDao
import com.example.quicknoteapp.domain.model.Note
import com.example.quicknoteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> = noteDao.getNotes()

    override suspend fun insertNote(note: Note) = noteDao.insert(note)

    override suspend fun deleteNote(id: Int) = noteDao.delete(id)
}
