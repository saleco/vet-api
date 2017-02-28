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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.vetty.model.AnimalType;
import com.vetty.model.Veterinary;
import org.junit.Test;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;

public class VeterinaryControllerTests extends BaseTests {


    @Test
    public void veterinarySignup() throws Exception {
        Veterinary veterinary = new Veterinary();
        veterinary.setName("Ana Paula Cardoso");
        veterinary.setUsername("ap2cardoso21");
        veterinary.setPassword("123455");

        List<AnimalType> animalTypeList = new ArrayList<AnimalType>();
        AnimalType feline = new AnimalType();
        feline.setId(2);
        animalTypeList.add(feline);
        AnimalType canine = new AnimalType();
        canine.setId(3);
        animalTypeList.add(canine);
        veterinary.setAnimalTypes(animalTypeList);

        this.mockMvc.perform(post("/veterinary")
                .content(asJsonString(veterinary))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void veterinaryUpdate() throws Exception {
        Veterinary veterinary = new Veterinary();
        veterinary.setId(1);
        veterinary.setName("Dudinha Vilela");

        List<AnimalType> animalTypeList = new ArrayList<AnimalType>();
        AnimalType feline = new AnimalType();
        feline.setId(2);
        animalTypeList.add(feline);
        AnimalType canine = new AnimalType();
        canine.setId(3);
        animalTypeList.add(canine);
        veterinary.setAnimalTypes(animalTypeList);

        this.mockMvc.perform(put("/veterinary")
                .content(asJsonString(veterinary))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
