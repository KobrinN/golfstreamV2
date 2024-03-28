package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.*;
import ru.golfstream.project.service.impl.EmployeeServiceImpl;
import ru.golfstream.project.service.impl.TypeEmployeeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final TypeEmployeeServiceImpl typeEmployeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/rate")
    public ResponseEntity<List<TypeEmployeeDto>> getAllType() {
        return ResponseEntity.ok(typeEmployeeService.findAll());
    }

    @GetMapping("/{id}/route")
    public ResponseEntity<List<RouteDto>> getTypeEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.findRouteOfEmployee(id));
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<Double> getSalary(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getSalary(id));
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody NewOrUpdateEmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.add(request));
    }

    @PostMapping("/rate")
    public ResponseEntity<Integer> addRate(@RequestBody NewOrUpdateTypeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(typeEmployeeService.add(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/rate/{id}")
    public ResponseEntity<?> deleteRate(@PathVariable Integer id) {
        typeEmployeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Integer id, @RequestBody NewOrUpdateEmployeeRequest request) {
        return ResponseEntity.ok(employeeService.update(id, request));
    }

    @PatchMapping("/rate/{id}")
    public ResponseEntity<TypeEmployeeDto> updateType(@PathVariable Integer id, @RequestBody NewOrUpdateTypeRequest request) {
        return ResponseEntity.ok(typeEmployeeService.update(id, request));
    }
}
