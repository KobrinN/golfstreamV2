package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.response.EmployeeDto;
import ru.golfstream.project.rest.dto.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    EmployeeDto findById(Long id);

    List<EmployeeDto> findAll();

    Double getSalary(Long id);

    Long add(EmployeeRequest request);

    EmployeeDto delete(Long id);

    EmployeeDto update(Long id, EmployeeRequest request);
}
