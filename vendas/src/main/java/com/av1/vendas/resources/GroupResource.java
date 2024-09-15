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
import com.av1.vendas.domains.Group;
import com.av1.vendas.domains.dtos.GroupDTO;
import com.av1.vendas.services.GroupService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/groupproduct")
@Tag(name = "Groupproduct", description = "API para gerenciamento de grupos")
public class GroupResource {

    @Autowired
    private GroupService groService;

    @Operation(summary = "Listar todos os grupos", description = "Retorna uma lista com todos os grupos")
    @GetMapping //exemplo http://localhost:8080/groupproduct
    public ResponseEntity<List<GroupDTO>> findAll(){
        return ResponseEntity.ok().body(groService.findAll());
    }

    @Operation(summary = "Buscar grupo por ID", description = "Retorna um grupo com base no ID fornecido")
    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/groupproduct/1
    public ResponseEntity<GroupDTO> findById(@PathVariable UUID id){
        Group obj = this.groService.findbyId(id);
        return ResponseEntity.ok().body(new GroupDTO(obj));
    }

    @Operation(summary = "Criar um novo grupo", description = "Cria um novo grupo com os dados fornecidos.")
    @PostMapping
    public ResponseEntity<GroupDTO> create(@RequestBody GroupDTO objDto) {
        Group newObj = groService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar grupo", description = "Atualiza os dados de um grupo existente com base no ID fornecido.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<GroupDTO> update(@PathVariable UUID id, @Valid @RequestBody GroupDTO objDto){
        Group Obj = groService.update(id, objDto);
        return ResponseEntity.ok().body(new GroupDTO(Obj));
    }

    @Operation(summary = "Deletar grupo", description = "Deleta um grupo com base no ID fornecido.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GroupDTO> delete(@PathVariable UUID id){
        groService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
