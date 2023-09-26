package com.example.adapter.in.dto;


import lombok.Data;
import lombok.Setter;


public class FilemetadataDto {
    private String name;
    private String path;
    private String genre;

    public FilemetadataDto(String name, String path, String genre) {
        this.name = name;
        this.path = path;
        this.genre = genre;
    }

    public FilemetadataDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
