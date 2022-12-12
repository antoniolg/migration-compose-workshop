package com.antonioleiva.notes.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.antonioleiva.notes.R

@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit) {

    val note by vm.note.collectAsState(initial = null)
    val isNewNote = note?.let { it.id == -1 } ?: true
    var title by remember(note) { mutableStateOf(note?.title ?: "") }
    var description by remember(note) { mutableStateOf(note?.description ?: "") }

    Scaffold(
        topBar = { DetailTopBar(isNewNote, onBack) },
    )
    { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    vm.save(title, description)
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                val buttonText = stringResource(
                    id = if (isNewNote) R.string.add_task else R.string.update_task
                )
                Text(buttonText.uppercase())
            }
        }
    }
}

@Composable
fun DetailTopBar(newNote: Boolean, onBack: () -> Unit) {
    val title =
        if (newNote) stringResource(id = R.string.new_note) else stringResource(id = R.string.edit_note)
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}
