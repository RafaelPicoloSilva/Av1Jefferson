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
import com.av1.vendas.domains.Product;
import com.av1.vendas.domains.dtos.ProductDTO;
import com.av1.vendas.services.ProductService;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/product")
@Tag(name = "Product", description = "API para gerenciamento de produtos")
public class ProductResource {

    @Autowired
    private ProductService prodService;

    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista com todos os produtos")
    @GetMapping //exemplo http://localhost:8080/product
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok().body(prodService.findAll());
    }

    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto com base no ID fornecido")
    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/product/1
    public ResponseEntity<ProductDTO> findById(@PathVariable UUID id){
        Product obj = this.prodService.findbyId(id);
        return ResponseEntity.ok().body(new ProductDTO(obj));
    }

    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto com os dados fornecidos.")
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO objDto) {
        Product newObj = prodService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente com base no ID fornecido.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @Valid @RequestBody ProductDTO objDto){
        Product Obj = prodService.update(id, objDto);
        return ResponseEntity.ok().body(new ProductDTO(Obj));
    }

    @Operation(summary = "Deletar produto", description = "Deleta um produto com base no ID fornecido.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable UUID id){
        prodService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
