package com.example.notes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.util.Note;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


//RecyclerView Adapter and OnNoteListener
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private ArrayList<Note> notes = new ArrayList<>();
    private OnNoteListener onNoteListener;

    public NoteAdapter(ArrayList<Note> notes, OnNoteListener onNoteListener) {
        this.notes = notes;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
        return new ViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.timestamp.setText(notes.get(position).getTimestamp());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, timestamp;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            timestamp = itemView.findViewById(R.id.timestamp);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }


}
