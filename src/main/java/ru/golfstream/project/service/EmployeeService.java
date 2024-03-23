package ru.golfstream.project.service;

import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.rest.dto.EmployeeDto;

import java.util.Optional;

public interface EmployeeService {
    EmployeeDto findById(Integer id);
    Employee findEmployeeById(Integer id);
}
