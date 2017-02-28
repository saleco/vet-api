package com.vetty.controller;

import com.vetty.model.Animal;
import com.vetty.model.Client;
import com.vetty.model.Veterinary;
import com.vetty.services.ClientService;
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
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/client")
    public ResponseEntity create(@Valid @RequestBody Client client) {
        clientService.create(client);
        return new ResponseEntity(client, HttpStatus.OK);
    }


}
