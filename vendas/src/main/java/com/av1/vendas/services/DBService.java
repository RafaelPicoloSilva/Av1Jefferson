package com.av1.vendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.av1.vendas.domains.Sale;
import com.av1.vendas.domains.Seller;
import com.av1.vendas.domains.Client;
import com.av1.vendas.domains.Group;
import com.av1.vendas.domains.Product;
import com.av1.vendas.domains.enums.SaleStatus;
import com.av1.vendas.repositories.SaleRepository;
import com.av1.vendas.repositories.SellerRepository;
import com.av1.vendas.repositories.ClientRepository;
import com.av1.vendas.repositories.GroupRepository;
import com.av1.vendas.repositories.ProductRepository;

@Service
public class DBService {

    @Autowired
    private SellerRepository sellRepo;
    
    @Autowired
    private ClientRepository clienRepo;

    @Autowired
    private GroupRepository groRepo;

    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private SaleRepository saleRepo;

    public void initDB()
    {
        Seller sell01 = new
            Seller(null, "Rafael","Silva",
            "49045013819","rafael@gmail.com", "123");
        
        Client cli01 = new Client(null, "Junin", "Zanquis",
        "02569095099",  "junin@gmail.com", "123");

        Client cli02 = new Client(null, "Rafael", "Silva",
        "02569095044",  "rafaelsilva@gmail.com", "123");

        Group gro01 = new Group(null, "Group 1");

        Product prod01 = new Product(null,"Product Test", "Pack", 15.00, 10.00, gro01);

        Sale sale01 = new Sale(null, 2500.00, "Sale test", SaleStatus.OPEN, sell01, cli01, prod01);

        sellRepo.save(sell01);
        clienRepo.save(cli01);
        clienRepo.save(cli02);
        groRepo.save(gro01);
        prodRepo.save(prod01);
        saleRepo.save(sale01);
        
    }
    
}
