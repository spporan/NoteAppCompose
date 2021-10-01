package com.poran.noteappcompose.feature_note.domain.utlil

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
