package com.example.quicknoteapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknoteapp.domain.model.Note
import com.example.quicknoteapp.data.local.NoteDao
import com.example.quicknoteapp.domain.usecase.AddNoteUseCase
import com.example.quicknoteapp.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    //Flow<List<Note>> will be converted into a StateFlow, so that UI observer gets the last emitted value whenever it starts collecting, better than waiting for the flow to be emitted sequentially.
    val notes: StateFlow<List<Note>> = getNotesUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            addNoteUseCase(Note(title = title, content = content))
        }
    }
}
