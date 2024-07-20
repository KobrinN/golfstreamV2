package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.response.EmployeeResponse;
import ru.golfstream.project.rest.dto.response.RouteResponse;
import ru.golfstream.project.rest.dto.response.TypeEmployeeResponse;
import ru.golfstream.project.rest.dto.request.EmployeeRequest;
import ru.golfstream.project.rest.dto.request.TypeEmployeeRequest;
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
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @GetMapping("/rate")
    public ResponseEntity<List<TypeEmployeeResponse>> getAllType() {
        return ResponseEntity.ok(typeEmployeeService.getAll());
    }

    @GetMapping("/{id}/route")
    public ResponseEntity<List<RouteResponse>> getTypeEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getRouteOfEmployee(id));
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<Double> getSalary(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getSalary(id));
    }

    @PostMapping
    public ResponseEntity<Long> add(@Validated @RequestBody EmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.post(request));
    }

    @PostMapping("/rate")
    public ResponseEntity<Long> addRate(@Validated @RequestBody TypeEmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(typeEmployeeService.post(request));
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
    public ResponseEntity<EmployeeResponse> update(@PathVariable Long id, @Validated @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.edit(id, request));
    }

    @PatchMapping("/rate/{id}")
    public ResponseEntity<TypeEmployeeResponse> updateType(@PathVariable Long id, @Validated @RequestBody TypeEmployeeRequest request) {
        return ResponseEntity.ok(typeEmployeeService.edit(id, request));
    }
}
