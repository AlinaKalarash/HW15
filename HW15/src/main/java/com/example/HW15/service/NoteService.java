package com.example.HW15.service;

import com.example.HW15.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class NoteService {
    private List<Note> notes = new ArrayList<>();

    public List<Note> listAll() {
        return this.notes;
    }

    public Note add(Note note) {
        long i = new Random().nextInt();
        note.setId(i);
        notes.add(note);
        return note;
    }

    public void deleteById(long id) {
        Note note = getById(id);
        this.notes.remove(note);
    }

    public void update(Note note) {
        long id = note.getId();
        Note updateNote = getById(id);
        updateNote.setTitle(note.getTitle());
        updateNote.setContent(note.getContent());
    }

    public Note getById(long id) {
        return this.notes.stream()
                .filter(note -> note.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Note with id "+ id + " is not found"));
    }

}
