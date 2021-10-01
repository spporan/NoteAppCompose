package com.poran.noteappcompose.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.poran.noteappcompose.feature_note.domain.utlil.NoteOrder
import com.poran.noteappcompose.feature_note.domain.utlil.OrderType

@Composable
fun OrderSection(
    modifier: Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
           DefaultRadioButton(
               text = "Title",
               selected = noteOrder is NoteOrder.Title,
               onClick = {
                   onOrderChange(NoteOrder.Title(noteOrder.orderType))
               })
           Spacer(modifier = Modifier.width(8.dp))
           DefaultRadioButton(
               text = "Date",
               selected = noteOrder is NoteOrder.Date,
               onClick = {
                   onOrderChange(NoteOrder.Date(noteOrder.orderType))
               })
           Spacer(modifier = Modifier.width(8.dp))
           DefaultRadioButton(
               text = "Color",
               selected = noteOrder is NoteOrder.Color,
               onClick = {
                   onOrderChange(NoteOrder.Color(noteOrder.orderType))
               })
       }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                selected = noteOrder.orderType is OrderType.Ascending,
                onClick = {
                    onOrderChange(noteOrder.copy(OrderType.Ascending))
                })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = noteOrder.orderType is OrderType.Descending,
                onClick = {
                    onOrderChange(noteOrder.copy(OrderType.Descending))
                })
        }
    }
}