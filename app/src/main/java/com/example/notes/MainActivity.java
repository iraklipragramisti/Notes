package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.notes.adapters.NoteAdapter;
import com.example.notes.database.NoteRepository;
import com.example.notes.util.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnNoteListener {

    private RecyclerView recyclerView;
    private ArrayList<Note> notes = new ArrayList<>();
    private NoteAdapter noteAdapter;
    private NoteRepository noteRepository;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Custom Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing
        noteRepository = new NoteRepository(this);
        recyclerView = findViewById(R.id.recyclerView);
        add = findViewById(R.id.add);
        initRecyclerView();
        retrieveNotes();
        insertFakes();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void retrieveNotes(){
        noteRepository.retrieveNotesTask().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if (notes.size() > 0){
                    notes.clear();
                }
                if (notes != null){
                    notes.addAll(notes);
                }
                noteAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        noteAdapter = new NoteAdapter(notes, this);
        recyclerView.setAdapter(noteAdapter);
    }

    private void insertFakes(){
        for (int i = 0; i < 100; i++){
            Note note = new Note();
            note.setTitle("Title" + i);
            note.setContent("Content" + i);
            note.setTimestamp("Jan 2000");
            notes.add(note);
        }
        noteAdapter.notifyDataSetChanged();
    }
    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("selected_note", notes.get(position));
        startActivity(intent);
    }
}

