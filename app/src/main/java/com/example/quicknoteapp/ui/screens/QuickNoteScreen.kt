package com.example.quicknoteapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quicknoteapp.domain.model.Note
import com.example.quicknoteapp.ui.viewmodels.NoteViewModel


@Composable
fun QuickNoteScreen(noteViewModel: NoteViewModel = hiltViewModel()){
    val notes by noteViewModel.notes.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        NoteInput{
            title, content -> noteViewModel.addNote(title, content)
        }

        LazyColumn {
            items(notes) {
                note -> NoteItem(note = note, onDelete = {noteViewModel.deleteNote(note) })
            }
        }
    }
}


@Composable
fun NoteInput(onAddNote: (String, String) -> Unit){
    var title by remember {mutableStateOf("")}
    var content by remember { mutableStateOf("") }

    Column {
        TextField(
            value = title,
            onValueChange = {title = it},
            label = { Text("Title") }
        )
        TextField(
            value = title,
            onValueChange = {content = it},
            label = {Text("content")}
        )
        Button( onClick = { onAddNote.invoke(title, content); title = ""; content = ""}){
            Text("Add Note")
        }
    }
}
@Composable
fun NoteItem(note: Note, onDelete: () -> Unit){
    Row(
        Modifier
           .fillMaxSize()
           .padding()
           .background(MaterialTheme.colorScheme.surface)
           .clickable { onDelete() },
        verticalAlignment = Alignment.CenterVertically)
    {
        Column(Modifier.weight(1f).padding(8.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = note.title, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

