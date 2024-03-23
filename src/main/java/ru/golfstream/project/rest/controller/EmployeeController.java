package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.EmployeeDto;
import ru.golfstream.project.rest.dto.RouteDto;
import ru.golfstream.project.rest.dto.TypeEmployeeDto;
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
    public ResponseEntity<List<EmployeeDto>> getAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(employeeService.findById(id));
    }
    @GetMapping("/rate")
    public ResponseEntity<List<TypeEmployeeDto>> getAllType(){
        return ResponseEntity.ok(typeEmployeeService.findAll());
    }

    @GetMapping("/{id}/route")
    public ResponseEntity<List<RouteDto>> getTypeEmployeeById(@PathVariable Integer id){
        return ResponseEntity.ok(employeeService.findRouteOfEmployee(id));
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<Double> getSalary(@PathVariable Integer id){
        return ResponseEntity.ok(employeeService.getSalary(id));
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody NewOrUpdateEmployeeRequest request){

    }
    @PostMapping("/rate")

    @DeleteMapping("/{id}")
    @DeleteMapping("/rate/{id}")


    @PatchMapping("/{id}")
    @PatchMapping("/rate/{id}")
}
