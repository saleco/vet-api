/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vetty;

import com.vetty.model.Animal;
import com.vetty.model.AnimalType;
import com.vetty.model.Client;
import com.vetty.model.Veterinary;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTests extends BaseTests {


    @Test
    public void clientSignup() throws Exception {
        Client client = new Client();
        client.setName("Sallo Szrajbman");
        client.setUsername("saleco2");
        client.setPassword("123456");

        List<Animal> animals = new ArrayList<Animal>();
        Animal animal = new Animal();
        animal.setName("Pudim");
        DateTime birthDate = new DateTime();
        birthDate.minusMonths(6);
        animal.setBirthDate(birthDate.toDate());

        AnimalType animalType = new AnimalType();
        animalType.setId(2);
        animal.setAnimalType(animalType);
        animals.add(animal);

        client.setAnimals(animals);
        this.mockMvc.perform(post("/client")
                .content(asJsonString(client))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
