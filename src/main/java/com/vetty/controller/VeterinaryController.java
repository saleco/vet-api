package com.vetty.controller;

import com.vetty.model.Attendance;
import com.vetty.model.Veterinary;
import com.vetty.repository.VeterinaryRepository;
import com.vetty.services.VeterinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by devfacotry on 2/27/17.
 */
@RestController
public class VeterinaryController {

    @Autowired
    private VeterinaryService veterinaryService;

    @PostMapping(value = "/veterinary")
    public ResponseEntity create(@Valid @RequestBody Veterinary veterinary) {
        veterinaryService.create(veterinary);

        return new ResponseEntity(veterinary, HttpStatus.OK);
    }

    @PutMapping(value = "/veterinary")
    public ResponseEntity update(@RequestBody Veterinary veterinary) {
        Assert.notNull(veterinary.getId());
        Assert.notNull(veterinary.getName());
        veterinaryService.update(veterinary);

        return new ResponseEntity(veterinary, HttpStatus.OK);
    }


}
