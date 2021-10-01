package com.poran.noteappcompose.feature_note.presentation.notes

import com.poran.noteappcompose.feature_note.domain.model.Note
import com.poran.noteappcompose.feature_note.domain.utlil.NoteOrder

sealed class NotesEvent {

    data class OrderNote(val noteOrder: NoteOrder): NotesEvent()

    data class DeleteNote(val note: Note): NotesEvent()

    object RestoreNote: NotesEvent()

    object ToggleOrderSection: NotesEvent()

}
