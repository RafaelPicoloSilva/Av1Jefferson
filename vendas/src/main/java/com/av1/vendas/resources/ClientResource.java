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
import com.av1.vendas.domains.Client;
import com.av1.vendas.domains.dtos.ClientDTO;
import com.av1.vendas.services.ClientService;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/client")
@Tag(name = "Client", description = "API para gerenciamento de clientes")
public class ClientResource {

    @Autowired
    private ClientService clienService;

    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista com todos os clientes")
    @GetMapping //exemplo http://localhost:8080/client
    public ResponseEntity<List<ClientDTO>> findAll(){
        return ResponseEntity.ok().body(clienService.findAll());
    }

    @Operation(summary = "Buscar cliente por ID", description = "Retorna um cliente com base no ID fornecido")
    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/client/1
    public ResponseEntity<ClientDTO> findById(@PathVariable UUID id){
        Client obj = this.clienService.findbyId(id);
        return ResponseEntity.ok().body(new ClientDTO(obj));
    }

    @Operation(summary = "Buscar cliente por CPF", description = "Retorna um cliente com base no CPF fornecido.")
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<ClientDTO> findByCpf(@PathVariable String cpf){
        Client obj = this.clienService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new ClientDTO(obj));
    }

    @Operation(summary = "Buscar cliente por email", description = "Retorna um cliente com base no email fornecido.")
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<ClientDTO> findByEmail(@PathVariable String email){
        Client obj = this.clienService.findbyEmail(email);
        return ResponseEntity.ok().body(new ClientDTO(obj));
    }


    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente com os dados fornecidos.")
    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO objDto) {
        Client newObj = clienService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente com base no ID fornecido.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable UUID id, @Valid @RequestBody ClientDTO objDto){
        Client Obj = clienService.update(id, objDto);
        return ResponseEntity.ok().body(new ClientDTO(Obj));
    }

    @Operation(summary = "Deletar cliente", description = "Deleta um cliente com base no ID fornecido.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable UUID id){
        clienService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
