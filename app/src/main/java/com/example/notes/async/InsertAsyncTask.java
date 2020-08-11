package com.example.notes.async;

import android.os.AsyncTask;

import com.example.notes.database.NoteDAO;
import com.example.notes.util.Note;

public class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

    private NoteDAO noteDAO;

    public InsertAsyncTask(NoteDAO dao) {
        noteDAO = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        noteDAO.insertNotes(notes);
        return null;
    }
}
