package com.av1.vendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.av1.vendas.domains.Client;
import com.av1.vendas.domains.dtos.ClientDTO;
import com.av1.vendas.repositories.ClientRepository;
import com.av1.vendas.services.exceptions.DataIntegrityViolationException;
import com.av1.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clienRepo;

    public List<ClientDTO> findAll(){
        return clienRepo.findAll().stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
    }

    public Client findbyId(UUID id){
        Optional<Client> obj = clienRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
    }

    public Client findbyCpf(String cpf){
        Optional<Client> obj = clienRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CPF: "+cpf));    
    }

    public Client findbyEmail(String email){
        Optional<Client> obj = clienRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Email:"+email));
    }

    public Client create(ClientDTO objDto){
        objDto.setId(null);
        ValidarPorCPFeEmail(objDto);
        Client newObj = new Client(objDto);
        return clienRepo.save(newObj);
    }

    public Client update(UUID id, ClientDTO objDto){
        objDto.setId(id);
        Client oldObj = findbyId(id);
        ValidarPorCPFeEmail(objDto);
        oldObj = new Client(objDto);
        return clienRepo.save(oldObj);
    }
    
    public void delete(UUID id){
        Client obj = findbyId(id);
        if (obj.getSales().size()>0) {
            throw new DataIntegrityViolationException("Vendedor não pode ser deletado pois possui vendas!");
        }
        clienRepo.deleteById(id);
    }
    
    private void ValidarPorCPFeEmail(ClientDTO objDto) {
        Optional<Client> obj = clienRepo.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        Optional<Client> obj2 = clienRepo.findByEmail(objDto.getEmail());
        if (obj2.isPresent() && obj2.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
