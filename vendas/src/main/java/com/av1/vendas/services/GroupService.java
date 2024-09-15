package com.av1.vendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.av1.vendas.domains.Group;
import com.av1.vendas.domains.dtos.GroupDTO;
import com.av1.vendas.repositories.GroupRepository;
import com.av1.vendas.services.exceptions.DataIntegrityViolationException;
import com.av1.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groRepo;

    public List<GroupDTO> findAll(){
        return groRepo.findAll().stream().map(obj -> new GroupDTO(obj)).collect(Collectors.toList());
    }

    public Group findbyId(UUID id){
        Optional<Group> obj = groRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
    }

    public Group create(GroupDTO objDto){
        objDto.setId(null);
        Group newObj = new Group();
        return groRepo.save(newObj);
    }

    public Group update(UUID id, GroupDTO objDto){
        objDto.setId(id);
        Group oldObj = findbyId(id);
        oldObj = new Group();
        return groRepo.save(oldObj);
    }
    
    public void delete(UUID id){
        Group obj = findbyId(id);
        if (obj.getProducts().size()>0) {
            throw new DataIntegrityViolationException("Grupo não pode ser deletado pois possui produtos!");
        }
        groRepo.deleteById(id);
    }
    
    }

