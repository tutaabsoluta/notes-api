package com.alonso.notes_api.controller;

import com.alonso.notes_api.model.NoteEntity;
import com.alonso.notes_api.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController( NoteService noteService ) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteEntity> getAllNotes() {
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteEntity> getNoteById( @PathVariable Long id ) {
        return noteService.findById( id )
                .map( ResponseEntity::ok )
                .orElse( ResponseEntity.notFound().build() );
    }
}
