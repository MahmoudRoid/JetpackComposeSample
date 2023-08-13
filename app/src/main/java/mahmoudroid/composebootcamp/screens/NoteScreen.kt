package mahmoudroid.composebootcamp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mahmoudroid.composebootcamp.R
import mahmoudroid.composebootcamp.components.NoteButton
import mahmoudroid.composebootcamp.components.NoteInputText
import mahmoudroid.composebootcamp.data.NotesDataSource
import mahmoudroid.composebootcamp.model.Note


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {

        // toolbar
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.note_app)) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.LightGray
            ),
            actions = {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "")
            }
        )

        // text fields
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            NoteInputText(
                modifier = Modifier.padding(vertical = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isWhitespace() || char.isLetter()
                        }) {
                        title = it
                    }
                }
            )

            NoteInputText(
                modifier = Modifier.padding(vertical = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isWhitespace() || char.isLetter()
                        }) {
                        description = it
                    }
                }
            )

            NoteButton(text = stringResource(id = R.string.save)) {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    // save
                    onAddNote(
                        Note(
                            title = title,
                            description = description,
                        )
                    )
                    title = ""
                    description = ""
                    Toast.makeText(context,"Note Saved Successfully", Toast.LENGTH_SHORT).show()
                }
            }

        }

        // list
        Divider(modifier = Modifier.padding(10.dp))

        LazyColumn {
            items(items = notes) { note ->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(it)
                })
            }
        }


    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {

    Surface(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp),
        color = Color(0xFFDFE6EB),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onNoteClicked.invoke(note)
                }
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title, style = MaterialTheme.typography.titleSmall)
            Text(text = note.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = note.date, style = MaterialTheme.typography.bodySmall)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(
        NotesDataSource().loadNotes(),
        {},
        {},
    )
}