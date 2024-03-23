package ru.golfstream.project.rest.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.entity.Purchase;
import ru.golfstream.project.rest.dto.PurchaseDto;
import ru.golfstream.project.rest.dto.VoucherDto;
import ru.golfstream.project.service.impl.VoucherServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/voucher")
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherServiceImpl voucherService;

    @GetMapping("/all")
    public ResponseEntity<List<VoucherDto>> getAll(){
        return ResponseEntity.ok(voucherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(voucherService.findById(id));
    }
    @GetMapping("/{id}/purchase")
    public ResponseEntity<List<PurchaseDto>> getPurchaseOfVoucher(@PathVariable Integer id){
        return ResponseEntity.ok(voucherService.findPurchaseOfVoucher(id));
    }
    @PostMapping

    @DeleteMapping("/{id}")

    @PatchMapping("/{id}")

}
