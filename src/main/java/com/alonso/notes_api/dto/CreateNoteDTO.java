package com.alonso.notes_api.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateNoteDTO {
    @NotBlank(message = "The title is required")
    @Size(max = 100, message = "The title can't contain over 100 characters")
    private String title;

    @NotBlank(message = "The content of the note is required")
    @Size(max = 300, message = "The content can't contain over 300 characters")
    private String content;

    public CreateNoteDTO( String title, String content ) {
        this.content = content;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
