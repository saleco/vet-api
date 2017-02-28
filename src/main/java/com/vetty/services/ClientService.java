package com.vetty.services;

import com.vetty.model.Animal;
import com.vetty.model.Client;
import com.vetty.model.Veterinary;
import com.vetty.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by devfacotry on 2/28/17.
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public void create(Client client){
        clientRepository.create(client);
        clientRepository.createClientAnimal(client);
    }

}
