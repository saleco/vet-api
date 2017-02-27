package com.vetty.model;

import java.util.List;

/**
 * Created by devfacotry on 2/27/17.
 */
public class Veterinary {
    private int id;
    private String name;
    private List<AnimalType> animalTypes;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
