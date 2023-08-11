package mahmoudroid.composebootcamp.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import mahmoudroid.composebootcamp.data.NotesDataSource
import mahmoudroid.composebootcamp.model.Note

class NoteViewModel: ViewModel(){

    private val noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(
            NotesDataSource().loadNotes()
        )
    }

    fun addNote(note: Note){
        noteList.add(note)
    }

    fun removeNote(note: Note){
        noteList.remove(note)
    }

    fun getAllNotes(): List<Note> = noteList

}