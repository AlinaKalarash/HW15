package com.example.HW15.service;

import com.example.HW15.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class NoteService {
    List<Note> notes = new ArrayList<>();

    public List<Note> listAll() {
        return notes.stream().collect(Collectors.toList());
    }

    public Note add(Note note) {
        long i = new Random().nextInt();
        note.setId(i);
        notes.add(note);
        return note;
    }

    public void deleteById(long id) {
        Note note = getById(id);
        notes.remove(note);
    }

    public void update(Note note) {
        long id = note.getId();
        Note updateNote = getById(id);
        updateNote.setTitle(note.getTitle());
        updateNote.setContext(note.getContext());
    }

    public Note getById(long id) {
        return notes.stream()
                .filter(note -> note.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Note with id "+ id + " is not found"));
    }

}
