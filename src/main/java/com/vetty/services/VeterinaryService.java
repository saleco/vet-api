package com.vetty.services;

import com.vetty.model.Veterinary;
import com.vetty.repository.VeterinaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by devfacotry on 2/28/17.
 */
@Service
public class VeterinaryService {
    @Autowired
    private VeterinaryRepository veterinaryRepository;

    @Transactional
    public void create(Veterinary veterinary){
        veterinaryRepository.create(veterinary);
        veterinaryRepository.createVeterinaryAnimalTypes(veterinary);

    }

    public void update(Veterinary veterinary) {
        veterinaryRepository.update(veterinary);

        if(!veterinary.getAnimalTypes().isEmpty()){
            veterinaryRepository.updateVeterinaryAnimalTypes(veterinary);
        }
    }
}
