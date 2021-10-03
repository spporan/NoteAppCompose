package com.poran.noteappcompose.feature_note.presentation.add_edit_notes

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.poran.noteappcompose.feature_note.domain.model.Note
import com.poran.noteappcompose.feature_note.presentation.add_edit_notes.components.TransparentTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    val scaffoldState = rememberScaffoldState()

    val noteBackgroundAnimateAbleState = remember {
        Animatable(
            Color( if (noteColor != -1) noteColor else viewModel.noteColor.value)
        )
    }

    val  scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
                is AddEditNoteViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.SavedNote)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimateAbleState.value)
                .padding(16.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
               Note.noteColors.forEach { noteColor ->
                   val colorInt = noteColor.toArgb()
                   Box(
                       modifier = Modifier
                           .size(50.dp)
                           .shadow(15.dp, CircleShape)
                           .clip(CircleShape)
                           .background(noteColor)
                           .border(
                               width = 3.dp,
                               color = if (colorInt == viewModel.noteColor.value) Color.White
                               else Color.Transparent,
                               shape = CircleShape
                           )
                           .clickable {
                               scope.launch {
                                   noteBackgroundAnimateAbleState.animateTo(
                                       targetValue = Color(colorInt),
                                       animationSpec = tween(
                                           500
                                       )
                                   )
                               }
                               viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                           }
                   )
               }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                isSingleLine = true,
                isHintVisible = titleState.isHintVisible,
                textStyle = MaterialTheme.typography.h5,
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                isSingleLine = false,
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )

        }

    }

}