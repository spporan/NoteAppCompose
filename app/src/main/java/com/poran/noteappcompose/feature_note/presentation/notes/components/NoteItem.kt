package com.poran.noteappcompose.feature_note.presentation.notes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.poran.noteappcompose.feature_note.domain.model.Note
import java.sql.Timestamp


@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDeleteNote: ()-> Unit
) {
    Box (modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(note.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(note.color,0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f,),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = note.timeStamp.toFormattedTime(),
                    style = MaterialTheme.typography.body2
                )
                IconButton(
                    onClick = onDeleteNote,
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note")
                }
            }
        }

    }

}

fun Long.toFormattedTime(): String {
    val SECOND_MILLIS = 1000
    val MINUTE_MILLIS = 60 * SECOND_MILLIS
    val HOUR_MILLIS = 60 * MINUTE_MILLIS
    val DAY_MILLIS = 24 * HOUR_MILLIS

    val now = System.currentTimeMillis()
    if (this > now || this <= 0) {
        return Timestamp(this).toString()
    }


    val  diff = now - this;
    return when {
        diff < MINUTE_MILLIS -> {
            "just now";
        }
        diff < 2 * MINUTE_MILLIS -> {
            "a minute ago";
        }
        diff < 50 * MINUTE_MILLIS -> {
            "${diff / MINUTE_MILLIS} minutes ago";
        }
        diff < 90 * MINUTE_MILLIS -> {
            "an hour ago";
        }
        diff < 24 * HOUR_MILLIS -> {
            "${diff / HOUR_MILLIS} hours ago";
        }
        diff < 48 * HOUR_MILLIS -> {
            "yesterday";
        }
        else -> {
            "${diff / DAY_MILLIS} days ago";
        }
    }

}