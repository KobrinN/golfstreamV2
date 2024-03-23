package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.NewOrUpdateRouteRequest;
import ru.golfstream.project.rest.dto.RouteDto;
import ru.golfstream.project.rest.dto.VoucherDto;
import ru.golfstream.project.service.impl.RouteServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteServiceImpl routeService;

    @GetMapping("/all")
    public ResponseEntity<List<RouteDto>> getAllForClient(){
        return ResponseEntity.ok(routeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(routeService.getById(id));
    }

    @GetMapping("/{id}/vouchers")
    public ResponseEntity<List<VoucherDto>> getVouchersByRouteId(@PathVariable Integer id){
        return ResponseEntity.ok(routeService.getVouchersByRouteId(id));
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody NewOrUpdateRouteRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(routeService.add(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RouteDto> updateById(@PathVariable Integer id, @RequestBody NewOrUpdateRouteRequest request){
        return ResponseEntity.ok(routeService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        routeService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
