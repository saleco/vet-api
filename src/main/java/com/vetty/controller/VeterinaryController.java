package com.vetty.controller;

import com.vetty.model.Attendance;
import com.vetty.model.Veterinary;
import com.vetty.repository.VeterinaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by devfacotry on 2/27/17.
 */
@RestController
public class VeterinaryController {

    @Autowired
    private VeterinaryRepository veterinaryRepository;

    @PostMapping(value = "/veterinary")
    public ResponseEntity create(@RequestBody Veterinary veterinary) {

        veterinaryRepository.create(veterinary);

        return new ResponseEntity(veterinary, HttpStatus.OK);
    }


}
