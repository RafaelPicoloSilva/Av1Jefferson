package com.av1.vendas.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.av1.vendas.domains.Sale;
import com.av1.vendas.domains.dtos.SaleDTO;
import com.av1.vendas.services.SaleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/sale")
@Tag(name = "Sale", description = "API para gerenciamento de vendas")
public class SaleResource {

    @Autowired
    private SaleService saleService;

    @Operation(summary = "Buscar Venda por ID", description = "Retorna uma Venda com base no ID fornecido")
    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/serviceorder/1
    public ResponseEntity<SaleDTO> findByID(@PathVariable UUID id){
        Sale obj = this.saleService.findbyID(id);
        return ResponseEntity.ok().body(new SaleDTO(obj));
    }

    @Operation(summary = "Listar todas as vendas", description = "Retorna uma lista com todas as vendas")
    @GetMapping //exemplo http://localhost:8080/serviceorder
    public ResponseEntity<List<SaleDTO>> findAll(){
        return ResponseEntity.ok().body(saleService.findAll());
    }

    @Operation(summary = "Criar nova Venda", description = "Cria uma nova Venda com base nos dados fornecidos")
    @PostMapping
    public ResponseEntity<SaleDTO> create(@Valid @RequestBody SaleDTO objDto){
        Sale newObj = saleService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualiza Venda por id", description = "Atualiza uma Venda existente com os dados fornecidos")
    @PutMapping(value = "/{id}")
    public ResponseEntity<SaleDTO> update(@PathVariable UUID id, @Valid @RequestBody SaleDTO objDto) {
        Sale Obj = saleService.update(id, objDto);
        return ResponseEntity.ok().body(new SaleDTO(Obj));
    }
}
