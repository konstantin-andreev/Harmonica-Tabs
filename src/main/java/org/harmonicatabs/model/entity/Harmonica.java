package org.harmonicatabs.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "harmonicas")
public class Harmonica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


}
