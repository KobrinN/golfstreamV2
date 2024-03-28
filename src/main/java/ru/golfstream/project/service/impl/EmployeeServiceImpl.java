package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.CompletableToListenableFutureAdapter;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.entity.TypeEmployee;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.EmployeeRepo;
import ru.golfstream.project.repos.TypeEmployeeRepo;
import ru.golfstream.project.rest.dto.EmployeeDto;
import ru.golfstream.project.rest.dto.NewOrUpdateEmployeeRequest;
import ru.golfstream.project.rest.dto.RouteDto;
import ru.golfstream.project.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final TypeEmployeeRepo typeEmployeeRepo;
    private final RouteServiceImpl routeService;
    private final TypeEmployeeServiceImpl typeEmployeeService;

    @Override
    public EmployeeDto findById(Integer id) {
        Employee employee = proofEmployeeExist(id);
        return buildEmployeeDto(employee);
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return proofEmployeeExist(id);
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
        Employee employee = proofEmployeeExist(id);
        return employee.getRoutes().stream()
                .map(routeService::buildRouteDto)
                .collect(Collectors.toList());
    }

    @Override
    public Double getSalary(Integer id) {
        Employee employee = proofEmployeeExist(id);
        return employee.getType().getRate() * employee.getOpeningHours();
    }

    @Override
    public Integer add(NewOrUpdateEmployeeRequest request) {
        TypeEmployee typeEmployee = typeEmployeeService.proofTypeExist(request.getTypeId());
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setSurname(request.getSurname());
        employee.setSecondname(request.getSecondName());
        employee.setCountry(request.getCountry());
        employee.setOpeningHours(request.getOpeningHours());
        employee.setType(typeEmployee);

        employeeRepo.saveAndFlush(employee);
        return employee.getId();
    }

    @Override
    public EmployeeDto delete(Integer id) {
        Employee employee = proofEmployeeExist(id);
        employeeRepo.delete(employee);
        return buildEmployeeDto(employee);
    }

    @Override
    public EmployeeDto update(Integer id, NewOrUpdateEmployeeRequest request) {
        Employee employee = proofEmployeeExist(id);
        TypeEmployee typeEmployee = typeEmployeeService.proofTypeExist(request.getTypeId());

        employee.setName(request.getName());
        employee.setSurname(request.getSurname());
        employee.setSecondname(request.getSecondName());
        employee.setCountry(request.getCountry());
        employee.setOpeningHours(request.getOpeningHours());
        employee.setType(typeEmployee);

        return buildEmployeeDto(employee);
    }

    protected Employee proofEmployeeExist(Integer id){
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
