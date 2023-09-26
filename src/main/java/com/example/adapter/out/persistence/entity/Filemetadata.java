package com.example.adapter.out.persistence.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table()
public class Filemetadata {
    @Id
    private Long id;

    private String name;
    private String path;
    private String genre;

    public Filemetadata( String name, String path, String genre) {
        this.name = name;
        this.path = path;
        this.genre = genre;
    }

    public Filemetadata() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
