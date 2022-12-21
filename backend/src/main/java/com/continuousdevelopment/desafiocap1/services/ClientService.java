package com.continuousdevelopment.desafiocap1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.continuousdevelopment.desafiocap1.dto.ClientDTO;
import com.continuousdevelopment.desafiocap1.entities.Client;
import com.continuousdevelopment.desafiocap1.repositories.ClientRepository;
import com.continuousdevelopment.desafiocap1.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){		
		Page<Client> list = repository.findAll(pageRequest);		
		return list.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);
	}		
}
