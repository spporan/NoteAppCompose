package com.poran.noteappcompose.di

import android.app.Application
import androidx.room.Room
import com.poran.noteappcompose.feature_note.data.data_source.NoteDatabase
import com.poran.noteappcompose.feature_note.data.repository.NoteRepositoryImpl
import com.poran.noteappcompose.feature_note.domain.repository.NoteRepository
import com.poran.noteappcompose.feature_note.domain.use_case.AddNote
import com.poran.noteappcompose.feature_note.domain.use_case.DeleteNote
import com.poran.noteappcompose.feature_note.domain.use_case.GetNotes
import com.poran.noteappcompose.feature_note.domain.use_case.NoteUseCases
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.Database_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository)
        )
    }
}
