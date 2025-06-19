package com.alonso.notes_api.services;

import com.alonso.notes_api.model.NoteEntity;
import com.alonso.notes_api.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NoteService {

    //Declaro la propiedad, la inyecto y la asigno
    private final NoteRepository noteRepository;

    public NoteService( NoteRepository noteRepository ) {
        this.noteRepository = noteRepository;
    }

    public List<NoteEntity> findAll() {
        return noteRepository.findAll();
    }

    public Optional<NoteEntity> findById( Long id ) {
        return noteRepository.findById( id );
    }

    public NoteEntity save( NoteEntity note ) {
        return noteRepository.save( note );
    }

    public void deleteById( Long id ) {
        noteRepository.deleteById( id );
    }

}
