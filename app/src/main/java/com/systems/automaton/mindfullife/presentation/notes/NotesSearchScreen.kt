package com.systems.automaton.mindfullife.presentation.notes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.systems.automaton.mindfullife.R
import com.systems.automaton.mindfullife.presentation.util.Screen
import com.systems.automaton.mindfullife.util.Constants
import com.systems.automaton.mindfullife.util.settings.ItemView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesSearchScreen(
    navController: NavHostController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.notesUiState
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        var query by rememberSaveable {
            mutableStateOf("")
        }
        LaunchedEffect(query){viewModel.onEvent(NoteEvent.SearchNotes(query))}
        val focusRequester = remember { FocusRequester() }
        LaunchedEffect(true){focusRequester.requestFocus()}
        OutlinedTextField(
            value = query,
            onValueChange = {query = it},
            label = { Text(stringResource(R.string.search_notes)) },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .focusRequester(focusRequester)
        )
        if (state.noteView == ItemView.LIST){
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(state.searchNotes, key = {it.id}) { note ->
                    NoteItem(
                        note = note,
                        onClick = {
                            navController.navigate(
                                Screen.NoteDetailsScreen.route.replace(
                                    "{${Constants.NOTE_ID_ARG}}",
                                    "${note.id}"
                                ).replace(
                                    "{${Constants.FOLDER_ID}}",
                                    ""
                                )
                            )
                        }
                    )
                }
            }
        } else {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(150.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(12.dp)
            ){
                items(state.searchNotes){ note ->
                    key(note.id) {
                        NoteItem(
                            note = note,
                            onClick = {
                                navController.navigate(
                                    Screen.NoteDetailsScreen.route.replace(
                                        "{${Constants.NOTE_ID_ARG}}",
                                        "${note.id}"
                                    ).replace(
                                        "{${Constants.FOLDER_ID}}",
                                        ""
                                    )
                                )
                            },
                            modifier = Modifier.height(220.dp)
                        )
                    }
                }
            }
        }
    }
}