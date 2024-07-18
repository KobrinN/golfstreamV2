package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.response.EmployeeResponse;
import ru.golfstream.project.rest.dto.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService extends Service<EmployeeRequest, EmployeeResponse> {
    Double getSalary(Long id);

}
