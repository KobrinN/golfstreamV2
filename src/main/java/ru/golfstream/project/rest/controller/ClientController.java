package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.VoucherOfClientDto;
import ru.golfstream.project.rest.dto.ClientDto;
import ru.golfstream.project.rest.dto.NewClientRequest;
import ru.golfstream.project.service.ClientService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> getAll() {
        return ResponseEntity.ok(clientService.findAllClients());
    }

    @GetMapping("/{id}/vouchers")
    public ResponseEntity<List<VoucherOfClientDto>> getClientAndHisVouchers(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.findVouchersOfClient(id));
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody NewClientRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.addNewClient(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        clientService.deleteById(id);
       return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDto> edit(@PathVariable Integer id, @RequestBody ClientDto clientDto){
        return ResponseEntity.ok(clientService.edit(id, clientDto));
    }
}

