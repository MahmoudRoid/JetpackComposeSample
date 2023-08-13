package mahmoudroid.composebootcamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import mahmoudroid.composebootcamp.model.Note
import mahmoudroid.composebootcamp.screens.NoteScreen
import mahmoudroid.composebootcamp.screens.NoteViewModel
import mahmoudroid.composebootcamp.ui.theme.ComposeBootcampTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                //val noteViewModel = viewModel<NoteViewModel>() //also works
                val noteViewModel: NoteViewModel by viewModels()
                NotesApp(noteViewModel)
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel) {

    val noteList: List<Note> = noteViewModel.noteList.collectAsState().value

    NoteScreen(
       noteList,
        {
            // add note
            noteViewModel.addNote(it)
        },
        {
            // remove
            noteViewModel.removeNote(it)
        },
    )
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeBootcampTheme {
        content.invoke()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun DefaultPreview() {
    MyApp {

    }
}