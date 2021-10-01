package com.poran.noteappcompose.feature_note.presentation.notes

import com.poran.noteappcompose.feature_note.domain.model.Note
import com.poran.noteappcompose.feature_note.domain.utlil.NoteOrder
import com.poran.noteappcompose.feature_note.domain.utlil.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
