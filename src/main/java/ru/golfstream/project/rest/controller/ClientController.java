package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.response.UserResponse;
import ru.golfstream.project.rest.dto.response.PurchaseResponse;
import ru.golfstream.project.rest.dto.response.VoucherResponse;
import ru.golfstream.project.rest.dto.request.UserRequest;
import ru.golfstream.project.service.UserService;
import ru.golfstream.project.service.PurchaseService;
import ru.golfstream.project.service.VoucherService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final UserService clientService;
    private final VoucherService voucherService;
    private final PurchaseService purchaseService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(clientService.getAllUsers());
    }

    @GetMapping("/{id}/vouchers")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<VoucherResponse>> getClientAndHisVouchers(@PathVariable Long id){
        return ResponseEntity.ok(voucherService.findVouchersOfClient(id));
    }

    @PostMapping("/{idClient}/purchase/{idVoucher}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PurchaseResponse> buyVoucher(@PathVariable Long idClient, @PathVariable Long idVoucher){
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.buy(idClient, idVoucher));
    }

    @PostMapping("/add")
    public ResponseEntity<Long> add(@RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.post(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        clientService.delete(id);
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/purchase/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> refuseThePurchase(@PathVariable Long id){
        purchaseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> edit(@PathVariable Long id, @RequestBody UserRequest request){
        return ResponseEntity.ok(clientService.edit(id, request));
    }
}

