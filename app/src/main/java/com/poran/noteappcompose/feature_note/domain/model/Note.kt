package com.poran.noteappcompose.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poran.noteappcompose.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long = System.currentTimeMillis(),
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