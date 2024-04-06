package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.PurchaseDto;
import ru.golfstream.project.rest.dto.VoucherDto;
import ru.golfstream.project.rest.dto.VoucherOfClientDto;
import ru.golfstream.project.rest.dto.ClientDto;
import ru.golfstream.project.rest.dto.request.ClientRequest;
import ru.golfstream.project.service.ClientService;
import ru.golfstream.project.service.PurchaseService;
import ru.golfstream.project.service.VoucherService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final VoucherService voucherService;
    private final PurchaseService purchaseService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> getAll() {
        return ResponseEntity.ok(clientService.findAllClients());
    }

    @GetMapping("/{id}/vouchers")
    public ResponseEntity<List<VoucherOfClientDto>> getClientAndHisVouchers(@PathVariable Long id){
        return ResponseEntity.ok(voucherService.findVouchersOfClient(id));
    }

    @PostMapping("/{idClient}/purchase/{idVoucher}")
    public ResponseEntity<PurchaseDto> buyVoucher(@PathVariable Long idClient, @PathVariable Long idVoucher){
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.buy(idClient, idVoucher));
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody ClientRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.addNewClient(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        clientService.deleteById(id);
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/purchase/{id}")
    public ResponseEntity<?> refuseThePurchase(@RequestBody Long id){
        purchaseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDto> edit(@PathVariable Long id, @RequestBody ClientDto clientDto){
        return ResponseEntity.ok(clientService.edit(id, clientDto));
    }
}

