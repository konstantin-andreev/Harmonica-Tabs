package org.harmonicatabs.model.dtos;

public class AddHarmonicaDTO {
    private String name;
    private String harp_key;
    private String type;
    private String manufacturer;

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
}
