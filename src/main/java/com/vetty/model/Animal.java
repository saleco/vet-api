package com.vetty.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by devfacotry on 2/27/17.
 */
public class Animal {

    private Integer id;

    @NotNull
    private AnimalType animalType;

    @NotNull
    private Client client;

    @NotNull
    private String name;

    @NotNull
    private Date birthDate;

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
