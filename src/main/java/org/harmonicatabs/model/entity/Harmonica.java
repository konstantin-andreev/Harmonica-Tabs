package org.harmonicatabs.model.entity;

import jakarta.persistence.*;
import org.harmonicatabs.model.enums.HarmonicaManufacturer;
import org.harmonicatabs.model.enums.HarmonicaType;

@Entity
@Table(name = "harmonicas")
public class Harmonica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String harp_key;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HarmonicaManufacturer manufacturer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HarmonicaType type;

    @Column
    private int age;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private UserEntity owner;


    public Harmonica() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHarp_key() {
        return harp_key;
    }

    public void setHarp_key(String harp_key) {
        this.harp_key = harp_key;
    }

    public HarmonicaManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(HarmonicaManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public HarmonicaType getType() {
        return type;
    }

    public void setType(HarmonicaType type) {
        this.type = type;
    }
}
