package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.entity.TypeEmployee;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.EmployeeRepo;
import ru.golfstream.project.repos.TypeEmployeeRepo;
import ru.golfstream.project.rest.dto.mapper.EmployeeMapper;
import ru.golfstream.project.rest.dto.mapper.EmployeeMapperImpl;
import ru.golfstream.project.rest.dto.mapper.UserMapper;
import ru.golfstream.project.rest.dto.response.EmployeeResponse;
import ru.golfstream.project.rest.dto.request.EmployeeRequest;
import ru.golfstream.project.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper = new EmployeeMapperImpl();

    @Override
    public EmployeeResponse getById(Long id) {
        Employee employee = checkExistAndGetEmployeeById(id);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAll() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    public Double getSalary(Long id) {
        Employee employee = checkExistAndGetEmployeeById(id);
        return employee.getType().getRate() * employee.getOpeningHours();
    }

    @Override
    public Long post(EmployeeRequest request) {
        Employee employee = employeeMapper.toModel(request);
        employeeRepo.saveAndFlush(employee);
        return employee.getId();
    }

    @Override
    public void delete(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        employee.ifPresent(employeeRepo::delete);
    }

    @Override
    public EmployeeResponse edit(Long id, EmployeeRequest request) {
        Employee employee = checkExistAndGetEmployeeById(id);
        employee = employeeMapper.toModel(request);
        employeeRepo.save(employee);
        return employeeMapper.toResponse(employee);
    }

    private Employee checkExistAndGetEmployeeById(Long id){
        Optional<Employee> employee = employeeRepo.findByIdWithRoute(id);
        if(employee.isEmpty()) throw new NotFoundException("Not found EMPLOYEE with id: " + id + "!");
        return employee.get();
    }
}
