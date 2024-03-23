package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.CompletableToListenableFutureAdapter;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.EmployeeRepo;
import ru.golfstream.project.rest.dto.EmployeeDto;
import ru.golfstream.project.rest.dto.RouteDto;
import ru.golfstream.project.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final RouteServiceImpl routeService;

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

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream()
                .map(this::buildEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteDto> findRouteOfEmployee(Integer id) {
        Optional<Employee> employeeFromDb = employeeRepo.findById(id);
        if(employeeFromDb.isEmpty()){
            throw new NotFoundException("Нет работника с ID = " + id + "!");
        }
        return employeeFromDb.get().getRoutes().stream()
                .map(routeService::buildRouteDto)
                .collect(Collectors.toList());
    }

    @Override
    public Double getSalary(Integer id) {
        Optional<Employee> employeeFromDb = employeeRepo.findById(id);
        if(employeeFromDb.isEmpty()){
            throw new NotFoundException("Нет работника с ID = " + id + "!");
        }
        return employeeFromDb.get().getType().getRate() * employeeFromDb.get().getOpeningHours();
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
