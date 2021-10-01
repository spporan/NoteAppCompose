package com.poran.noteappcompose.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poran.noteappcompose.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val Database_NAME = "note_database"
    }

}