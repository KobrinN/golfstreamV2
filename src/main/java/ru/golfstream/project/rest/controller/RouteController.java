package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.response.RouteResponse;
import ru.golfstream.project.rest.dto.response.VoucherResponse;
import ru.golfstream.project.rest.dto.request.RouteRequest;
import ru.golfstream.project.service.RouteService;
import ru.golfstream.project.service.VoucherService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    private final VoucherService voucherService;

    @GetMapping("/all")
    public ResponseEntity<List<RouteResponse>> getAllForClient(){
        return ResponseEntity.ok(routeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(routeService.getById(id));
    }

    @GetMapping("/{id}/vouchers")
    public ResponseEntity<List<VoucherResponse>> getVouchersByRouteId(@PathVariable Long id){
        return ResponseEntity.ok(voucherService.getVouchersByRouteId(id));
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody RouteRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(routeService.post(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RouteResponse> updateById(@PathVariable Long id, @RequestBody RouteRequest request){
        return ResponseEntity.ok(routeService.edit(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        routeService.delete(id);
        return ResponseEntity.ok().build();
    }


}
