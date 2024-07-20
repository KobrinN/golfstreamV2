package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.response.PurchaseResponse;
import ru.golfstream.project.rest.dto.response.VoucherResponse;
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
    public ResponseEntity<List<VoucherResponse>> getAll(){
        return ResponseEntity.ok(voucherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(voucherService.getById(id));
    }
    @GetMapping("/{id}/purchase")
    public ResponseEntity<List<PurchaseResponse>> getPurchaseOfVoucher(@PathVariable Long id){
        return ResponseEntity.ok(purchaseService.getPurchaseOfVoucher(id));
    }
    @PostMapping
    public ResponseEntity<Long> add(@Validated @RequestBody VoucherRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(voucherService.post(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VoucherResponse> delete(@PathVariable Long id){
        voucherService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VoucherResponse> update(@PathVariable Long id, @Validated @RequestBody VoucherRequest request){
        return ResponseEntity.ok(voucherService.edit(id, request));
    }

}
