package com.codedev.mynotesapplication.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.codedev.mynotesapplication.presentation.Screen
import com.codedev.mynotesapplication.presentation.notes.components.FilterSection
import com.codedev.mynotesapplication.presentation.notes.components.NoteCard
import com.codedev.mynotesapplication.presentation.notes.components.TopAppBar
import com.codedev.mynotesapplication.ui.theme.TextDarkGray
import com.codedev.mynotesapplication.ui.theme.TextWhite

@Composable
fun NoteScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.noteState.value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route)
                },
                backgroundColor = TextDarkGray,
                elevation = FloatingActionButtonDefaults.elevation(5.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = TextWhite,
                    contentDescription = "Add note"
                )
            }
        },
        backgroundColor = TextDarkGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(7.5.dp)
                .background(Color.Transparent),
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            TopAppBar(
                onSearchClicked = {}
            )
            Spacer(modifier = Modifier.height(5.dp))
            FilterSection(
                onOrderChanged = {
                    viewModel.execute(NoteEvents.ChangeOrder(it))
                },
                noteOrder = viewModel.noteState.value.noteOrder
            )
            if (state.loading) {
                CircularProgressIndicator(
                    color = TextWhite,
                    strokeWidth = 4.dp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                ) {
                    items(state.notes.size) {
                        NoteCard(note = state.notes[it])
                    }
                }
        }
    }
}