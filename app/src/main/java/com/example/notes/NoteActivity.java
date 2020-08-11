package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.notes.database.NoteRepository;
import com.example.notes.util.Note;

import org.w3c.dom.Text;

public class NoteActivity extends AppCompatActivity{
    Note note = new Note();
    Bundle bundle;
    String title;
    TextView nameplace;
    EditText contents;
    NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        nameplace = findViewById(R.id.nameplace);
        contents = findViewById(R.id.contents);
        noteRepository = new NoteRepository(this);


        bundle = getIntent().getExtras();

        if (bundle.get("selected_note") != null){
            note = (Note) bundle.get("selected_note");
            nameplace.setText(note.getTitle());
            contents.setText(note.getContent());
        }


    }

    @Override
    protected void onStop() {
        super.onStop();

        note.setContent(contents.toString());
    }

    private void saveNewNote(){
        noteRepository.insertNoteTask(note);
    }


}
