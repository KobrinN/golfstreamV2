package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.NewOrUpdateTypeRequest;
import ru.golfstream.project.rest.dto.TypeEmployeeDto;

import java.util.List;

public interface TypeEmployeeService {
    List<TypeEmployeeDto> findAll();

    Integer add(NewOrUpdateTypeRequest request);

    TypeEmployeeDto delete(Integer id);

    TypeEmployeeDto update(Integer id, NewOrUpdateTypeRequest request);
}
