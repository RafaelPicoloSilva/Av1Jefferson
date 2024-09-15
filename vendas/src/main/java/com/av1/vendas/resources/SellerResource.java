package com.av1.vendas.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.av1.vendas.domains.Seller;
import com.av1.vendas.domains.dtos.SellerDTO;
import com.av1.vendas.services.SellerService;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/seller")
@Tag(name = "Seller", description = "API para gerenciamento de vendedores")
public class SellerResource {

    @Autowired
    private SellerService sellService;

    @Operation(summary = "Listar todos os vendedores", description = "Retorna uma lista com todos os vendedores")
    @GetMapping //exemplo http://localhost:8080/seller
    public ResponseEntity<List<SellerDTO>> findAll(){
        return ResponseEntity.ok().body(sellService.findAll());
    }

    @Operation(summary = "Buscar vendedor por ID", description = "Retorna um vendedor com base no ID fornecido")
    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/seller/1
    public ResponseEntity<SellerDTO> findById(@PathVariable UUID id){
        Seller obj = this.sellService.findbyId(id);
        return ResponseEntity.ok().body(new SellerDTO(obj));
    }

    @Operation(summary = "Buscar vendedor por CPF", description = "Retorna um vendedor com base no CPF fornecido.")
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<SellerDTO> findByCpf(@PathVariable String cpf){
        Seller obj = this.sellService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new SellerDTO(obj));
    }

    @Operation(summary = "Buscar vendedor por email", description = "Retorna um vendedor com base no email fornecido.")
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<SellerDTO> findByEmail(@PathVariable String email){
        Seller obj = this.sellService.findbyEmail(email);
        return ResponseEntity.ok().body(new SellerDTO(obj));
    }


    @Operation(summary = "Criar um novo vendedor", description = "Cria um novo vendedor com os dados fornecidos.")
    @PostMapping
    public ResponseEntity<SellerDTO> create(@RequestBody SellerDTO objDto) {
        Seller newObj = sellService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar vendedor", description = "Atualiza os dados de um vendedor existente com base no ID fornecido.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<SellerDTO> update(@PathVariable UUID id, @Valid @RequestBody SellerDTO objDto){
        Seller Obj = sellService.update(id, objDto);
        return ResponseEntity.ok().body(new SellerDTO(Obj));
    }

    @Operation(summary = "Deletar vendedor", description = "Deleta um vendedor com base no ID fornecido.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SellerDTO> delete(@PathVariable UUID id){
        sellService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
