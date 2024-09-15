package com.av1.vendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.av1.vendas.domains.Seller;
import com.av1.vendas.domains.dtos.SellerDTO;
import com.av1.vendas.repositories.SellerRepository;
import com.av1.vendas.services.exceptions.DataIntegrityViolationException;
import com.av1.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellRepo;

    public List<SellerDTO> findAll(){
        return sellRepo.findAll().stream().map(obj -> new SellerDTO(obj)).collect(Collectors.toList());
    }

    public Seller findbyId(UUID id){
        Optional<Seller> obj = sellRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
    }

    public Seller findbyCpf(String cpf){
        Optional<Seller> obj = sellRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CPF: "+cpf));    
    }

    public Seller findbyEmail(String email){
        Optional<Seller> obj = sellRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Email:"+email));
    }

    public Seller create(SellerDTO objDto){
        objDto.setId(null);
        ValidarPorCPFeEmail(objDto);
        Seller newObj = new Seller(objDto);
        return sellRepo.save(newObj);
    }

    public Seller update(UUID id, SellerDTO objDto){
        objDto.setId(id);
        Seller oldObj = findbyId(id);
        ValidarPorCPFeEmail(objDto);
        oldObj = new Seller(objDto);
        return sellRepo.save(oldObj);
    }
    
    public void delete(UUID id){
        Seller obj = findbyId(id);
        if (obj.getSales().size()>0) {
            throw new DataIntegrityViolationException("Vendedor não pode ser deletado pois possui vendas!");
        }
        sellRepo.deleteById(id);
    }
    
    private void ValidarPorCPFeEmail(SellerDTO objDto) {
        Optional<Seller> obj = sellRepo.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        Optional<Seller> obj2 = sellRepo.findByEmail(objDto.getEmail());
        if (obj2.isPresent() && obj2.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
