package com.av1.vendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.av1.vendas.domains.Product;
import com.av1.vendas.domains.dtos.ProductDTO;
import com.av1.vendas.repositories.ProductRepository;
import com.av1.vendas.services.exceptions.DataIntegrityViolationException;
import com.av1.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository prodRepo;

    public List<ProductDTO> findAll(){
        return prodRepo.findAll().stream().map(obj -> new ProductDTO(obj)).collect(Collectors.toList());
    }

    public Product findbyId(UUID id){
        Optional<Product> obj = prodRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
    }

    public Product create(ProductDTO objDto){
        objDto.setId(null);
        Product newObj = new Product();
        return prodRepo.save(newObj);
    }

    public Product update(UUID id, ProductDTO objDto){
        objDto.setId(id);
        Product oldObj = findbyId(id);
        oldObj = new Product();
        return prodRepo.save(oldObj);
    }
    
    public void delete(UUID id){
        Product obj = findbyId(id);
        if (obj.getSales().size()>0) {
            throw new DataIntegrityViolationException("Produto não pode ser deletado pois possui vendas!");
        }
        prodRepo.deleteById(id);
    }
    
    }