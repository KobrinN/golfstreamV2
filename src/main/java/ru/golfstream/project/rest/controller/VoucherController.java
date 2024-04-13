package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.PurchaseDto;
import ru.golfstream.project.rest.dto.VoucherDto;
import ru.golfstream.project.rest.dto.request.VoucherRequest;
import ru.golfstream.project.service.PurchaseService;
import ru.golfstream.project.service.VoucherService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/voucher")
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService voucherService;
    private final PurchaseService purchaseService;

    @GetMapping("/all")
    public ResponseEntity<List<VoucherDto>> getAll(){
        return ResponseEntity.ok(voucherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(voucherService.findById(id));
    }
    @GetMapping("/{id}/purchase")
    public ResponseEntity<List<PurchaseDto>> getPurchaseOfVoucher(@PathVariable Long id){
        return ResponseEntity.ok(purchaseService.findPurchaseOfVoucher(id));
    }
    @PostMapping
    public ResponseEntity<Long> add(@RequestBody VoucherRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(voucherService.add(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VoucherDto> delete(@PathVariable Long id){
        voucherService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VoucherDto> update(@PathVariable Long id, VoucherRequest request){
        return ResponseEntity.ok(voucherService.update(id, request));
    }

}
