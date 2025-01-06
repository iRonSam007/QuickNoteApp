package com.example.quicknoteapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO: Annotating domain/model violates CA
@Entity(
    tableName = "notes"
)
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val title: String,
    val content: String
)