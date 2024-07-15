package org.harmonicatabs.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
