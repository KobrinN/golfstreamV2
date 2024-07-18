package ru.golfstream.project.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.rest.dto.request.EmployeeRequest;
import ru.golfstream.project.rest.dto.response.EmployeeResponse;

@Mapper
public interface EmployeeMapper {
    @Mapping(target = "type.id", source = "request.idType")
    Employee toModel(EmployeeRequest request);
    EmployeeResponse toResponse(Employee employee);
}
