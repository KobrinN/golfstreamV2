package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.request.TypeRequest;
import ru.golfstream.project.rest.dto.TypeEmployeeDto;

import java.util.List;

public interface TypeEmployeeService {
    List<TypeEmployeeDto> findAll();

    Long add(TypeRequest request);

    TypeEmployeeDto delete(Long id);

    TypeEmployeeDto update(Long id, TypeRequest request);
}
