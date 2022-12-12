package com.antonioleiva.notes.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.antonioleiva.notes.R
import com.antonioleiva.notes.data.Note

@Composable
fun MainScreen(vm: MainViewModel, onAdd: () -> Unit, onClick: (Note) -> Unit) {
    Scaffold(
        topBar = { MainTopBar() },
        floatingActionButton = { MainFab(onAdd) }
    ) { padding ->
        val notes by vm.notes.collectAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(notes) { note ->
                MainListItem(note, onClick, vm::delete)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainListItem(note: Note, onClick: (Note) -> Unit, onDelete: (Note) -> Unit) {
    ListItem(
        text = { Text(note.title) },
        secondaryText = { Text(note.description) },
        trailing = {
            IconButton(onClick = { onDelete(note) }) {
                Icon(Icons.Default.Delete, contentDescription = stringResource(id = R.string.delete))
            }
        },
        modifier = Modifier.clickable { onClick(note) }
    )
}

@Composable
fun MainTopBar() {
    TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
}

@Composable
fun MainFab(onAdd: () -> Unit) {
    FloatingActionButton(onClick = onAdd) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add_task)
        )
    }
}
