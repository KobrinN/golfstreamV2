package ru.golfstream.project.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.rest.dto.request.EmployeeRequest;
import ru.golfstream.project.rest.dto.response.EmployeeResponse;

@Mapper
public interface EmployeeMapper {
    @Mapping(target = "type.id", source = "request.idType")
    Employee toModel(EmployeeRequest request);
    @Mapping(target = "idType", source = "employee.type.id")
    EmployeeRequest toRequest(Employee employee);
    EmployeeResponse toResponse(Employee employee);
    Employee toModel(EmployeeResponse response);
}
