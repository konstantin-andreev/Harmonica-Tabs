package org.harmonicatabs.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddHarmonicaDTO {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @NotBlank
    private String harp_key;
    @NotBlank
    private String type;
    @NotBlank
    private String manufacturer;
    private int age;

    public AddHarmonicaDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHarp_key() {
        return harp_key;
    }

    public void setHarp_key(String harp_key) {
        this.harp_key = harp_key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
