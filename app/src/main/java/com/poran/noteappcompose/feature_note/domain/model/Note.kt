package com.poran.noteappcompose.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poran.noteappcompose.ui.theme.*
import java.lang.Exception

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long = System.currentTimeMillis(),
    val color: Int,
    @PrimaryKey val id: Int
) {
    companion object {
        val noteColors = listOf(
            RedPink,
            RedOrange,
            LightGreen,
            Violet,
            BabyBlue
        )
    }
}

class InvalidNoteException(message: String): Exception(message)