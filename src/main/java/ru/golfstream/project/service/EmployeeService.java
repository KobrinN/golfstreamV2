package ru.golfstream.project.service;

import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.rest.dto.EmployeeDto;
import ru.golfstream.project.rest.dto.RouteDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDto findById(Integer id);
    Employee findEmployeeById(Integer id);

    List<EmployeeDto> findAll();

    List<RouteDto> findRouteOfEmployee(Integer id);

    Double getSalary(Integer id);
}
