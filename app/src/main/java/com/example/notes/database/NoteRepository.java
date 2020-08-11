package com.example.notes.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.notes.async.InsertAsyncTask;
import com.example.notes.util.Note;

import java.util.List;

public class NoteRepository {
    private NoteDatabase noteDatabase;

    public NoteRepository(Context context){
        noteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Note note){
        new InsertAsyncTask(noteDatabase.getNoteDao()).execute(note);
    }

    public void updateNote(Note note){

    }

    public LiveData<List<Note>> retrieveNotesTask(){
        return noteDatabase.getNoteDao().getNotes();
    }

    public void deleteNote(Note note){

    }
}
