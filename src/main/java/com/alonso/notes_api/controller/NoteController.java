package com.alonso.notes_api.controller;

import com.alonso.notes_api.dto.CreateNoteDTO;
import com.alonso.notes_api.model.NoteEntity;
import com.alonso.notes_api.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController( NoteService noteService ) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<?> getAllNotes() {
        List<NoteEntity> notes = noteService.findAll();
        if ( notes.isEmpty() ) {
            return ResponseEntity.ok().body(
                    Map.of("message", "There's no notes")
            );
        }
        return ResponseEntity.ok( notes );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id) {
        return noteService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.status(404).body(
                                Map.of("message", "The note with " + id + " was not found")
                        )
                );
    }
    @PostMapping
    public ResponseEntity<NoteEntity> createNote(@RequestBody @Valid CreateNoteDTO dto) {
        NoteEntity note = new NoteEntity();
        note.setTitle( dto.getTitle() );
        note.setContent( dto.getContent() );
        return ResponseEntity.ok( noteService.save( note ) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        boolean noteExists = noteService.findById(id).isPresent();

        if (!noteExists) {
            return ResponseEntity.status(404).body(
                    Map.of("message", "The note with " + id + " was not found")
            );
        }

        noteService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 sin body
    }

}
