package com.example.quicknoteapp.domain.usecase

import com.example.quicknoteapp.domain.model.Note
import com.example.quicknoteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> {
        return noteRepository.getNotes()
    }
}

class AddNoteUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        noteRepository.insertNote(note)
    }
}
