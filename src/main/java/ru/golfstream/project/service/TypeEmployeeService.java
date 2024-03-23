package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.TypeEmployeeDto;

import java.util.List;

public interface TypeEmployeeService {
    List<TypeEmployeeDto> findAll();
}
