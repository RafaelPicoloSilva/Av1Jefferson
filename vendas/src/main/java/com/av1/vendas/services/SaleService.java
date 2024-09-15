package com.av1.vendas.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.av1.vendas.domains.Sale;
import com.av1.vendas.domains.Seller;
import com.av1.vendas.domains.Client;
import com.av1.vendas.domains.Product;
import com.av1.vendas.domains.dtos.SaleDTO;
import com.av1.vendas.domains.enums.SaleStatus;
import com.av1.vendas.repositories.SaleRepository;
import com.av1.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;

    public Sale findbyID(UUID id){
        Optional<Sale> obj = saleRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado Não Encontrado! ID: " +id));
    }

    public List<SaleDTO> findAll(){
        return saleRepository.findAll().stream().map(obj -> new SaleDTO(obj)).collect(Collectors.toList());
    }

    private Sale newsSale(SaleDTO obj){
        Seller sell = sellerService.findbyId(obj.getSeller());
        Client clien = clientService.findbyId(obj.getClient());
        Product prod = productService.findbyId(obj.getProduct());

        Sale os = new Sale();
        if(obj.getId() != null){
            os.setId(obj.getId());
        }

        if(obj.getSaleStatus().equals(2)){
            obj.setDate(LocalDate.now());
        }

        os.setSeller(sell);
        os.setClient(clien);
        os.setProduct(prod);
        os.setSaleStatus(SaleStatus.toEnum(obj.getSaleStatus()));
        os.setTotal(obj.getTotal());
        os.setObs(obj.getObs());
        return os;
    }

    public Sale create(SaleDTO objDto){
        return saleRepository.save(newsSale(objDto));
    }

    public Sale update(UUID id, SaleDTO objDTO){
        objDTO.setId(id);
        Sale oldObj = findbyID(id);
        oldObj = newsSale(objDTO);
        return saleRepository.save(oldObj);
    }

}
