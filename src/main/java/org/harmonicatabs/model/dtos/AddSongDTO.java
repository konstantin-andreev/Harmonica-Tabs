package org.harmonicatabs.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddSongDTO {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @NotNull
    private LocalDate creationDate;
    @NotBlank
    private String genre;
    @Size(min = 2, max = 50)
    private String artistName;
    @NotBlank
    @Size(min = 1, max = 200)
    private String tab;

    public AddSongDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }
}
