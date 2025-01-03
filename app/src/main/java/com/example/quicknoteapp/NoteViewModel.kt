package com.example.quicknoteapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteDao: NoteDao
) : ViewModel()
{
    //convert the flow of List<Note> into a StateFlow, so that UI observer gets the last emitted value whenever it starts collecting, rather than waiting for the flow to be emitted sequentially.
    val notes: StateFlow<List<Note>> = noteDao.getNotes().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    fun addNote(title: String, content: String){
        viewModelScope.launch {
            noteDao.insert(Note(title = title, content = content))
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteDao.delete(note)
        }
    }

}