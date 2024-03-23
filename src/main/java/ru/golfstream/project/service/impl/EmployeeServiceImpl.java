package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.EmployeeRepo;
import ru.golfstream.project.rest.dto.EmployeeDto;
import ru.golfstream.project.service.EmployeeService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Override
    public EmployeeDto findById(Integer id) {
        Optional<Employee> employeeFromDb = employeeRepo.findById(id);

        if(employeeFromDb.isEmpty()){
            throw new NotFoundException("Не найден работник с id = " + id + "!");
        }

        return buildEmployeeDto(employeeFromDb.get());
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        Optional<Employee> employeeFromDb = employeeRepo.findById(id);

        if(employeeFromDb.isEmpty()){
            throw new NotFoundException("Не найден работник с id = " + id + "!");
        }

        return employeeFromDb.get();
    }

    private static EmployeeDto buildEmployeeDto(Employee employee){
        return EmployeeDto.builder()
                .name(employee.getName())
                .surname(employee.getSurname())
                .secondName(employee.getSecondname())
                .country(employee.getCountry())
                .openingHours(employee.getOpeningHours())
                .build();
    }
}
