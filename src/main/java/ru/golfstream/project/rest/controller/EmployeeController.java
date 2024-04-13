package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.EmployeeDto;
import ru.golfstream.project.rest.dto.RouteDto;
import ru.golfstream.project.rest.dto.TypeEmployeeDto;
import ru.golfstream.project.rest.dto.request.EmployeeRequest;
import ru.golfstream.project.rest.dto.request.TypeRequest;
import ru.golfstream.project.service.EmployeeService;
import ru.golfstream.project.service.RouteService;
import ru.golfstream.project.service.TypeEmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final RouteService routeService;
    private final TypeEmployeeService typeEmployeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/rate")
    public ResponseEntity<List<TypeEmployeeDto>> getAllType() {
        return ResponseEntity.ok(typeEmployeeService.findAll());
    }

    @GetMapping("/{id}/route")
    public ResponseEntity<List<RouteDto>> getTypeEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.findRouteOfEmployee(id));
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<Double> getSalary(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getSalary(id));
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody EmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.add(request));
    }

    @PostMapping("/rate")
    public ResponseEntity<Long> addRate(@RequestBody TypeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(typeEmployeeService.add(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/rate/{id}")
    public ResponseEntity<?> deleteRate(@PathVariable Long id) {
        typeEmployeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.update(id, request));
    }

    @PatchMapping("/rate/{id}")
    public ResponseEntity<TypeEmployeeDto> updateType(@PathVariable Long id, @RequestBody TypeRequest request) {
        return ResponseEntity.ok(typeEmployeeService.update(id, request));
    }
}
