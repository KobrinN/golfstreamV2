package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.entity.TypeEmployee;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.EmployeeRepo;
import ru.golfstream.project.repos.TypeEmployeeRepo;
import ru.golfstream.project.rest.dto.response.EmployeeDto;
import ru.golfstream.project.rest.dto.request.EmployeeRequest;
import ru.golfstream.project.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final TypeEmployeeRepo typeEmployeeRepo;

    @Override
    public EmployeeDto findById(Long id) {
        Employee employee = proofEmployeeExist(id);
        return buildEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream()
                .map(this::buildEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public Double getSalary(Long id) {
        Employee employee = proofEmployeeExist(id);
        return employee.getType().getRate() * employee.getOpeningHours();
    }

    @Override
    public Long add(EmployeeRequest request) {
        Optional<TypeEmployee> typeEmployeeFromBd = typeEmployeeRepo.findById(request.getTypeId());
        if(typeEmployeeFromBd.isEmpty()){
            throw new NotFoundException("Нет типа работника с ID = " + request.getTypeId() + "!");
        }
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setSurname(request.getSurname());
        employee.setSecondname(request.getSecondName());
        employee.setCountry(request.getCountry());
        employee.setOpeningHours(request.getOpeningHours());
        employee.setType(typeEmployeeFromBd.get());

        employeeRepo.saveAndFlush(employee);
        return employee.getId();
    }

    @Override
    public EmployeeDto delete(Long id) {
        Employee employee = proofEmployeeExist(id);
        employeeRepo.delete(employee);
        return buildEmployeeDto(employee);
    }

    @Override
    public EmployeeDto update(Long id, EmployeeRequest request) {
        Employee employee = proofEmployeeExist(id);
        Optional<TypeEmployee> typeEmployeeFromBd = typeEmployeeRepo.findById(id);
        if(typeEmployeeFromBd.isEmpty()){
            throw new NotFoundException("Нет типа работника с ID = " + id + "!");
        }

        employee.setName(request.getName());
        employee.setSurname(request.getSurname());
        employee.setSecondname(request.getSecondName());
        employee.setCountry(request.getCountry());
        employee.setOpeningHours(request.getOpeningHours());
        employee.setType(typeEmployeeFromBd.get());

        return buildEmployeeDto(employee);
    }

    protected Employee proofEmployeeExist(Long id){
        Optional<Employee> employeeFromDb = employeeRepo.findById(id);
        if(employeeFromDb.isEmpty()){
            throw new NotFoundException("Нет работника с ID = " + id + "!");
        }
        return employeeFromDb.get();
    }

    protected EmployeeDto buildEmployeeDto(Employee employee){
        return EmployeeDto.builder()
                .name(employee.getName())
                .surname(employee.getSurname())
                .secondName(employee.getSecondname())
                .country(employee.getCountry())
                .openingHours(employee.getOpeningHours())
                .build();
    }
}
