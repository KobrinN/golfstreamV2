package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.TypeEmployee;
import ru.golfstream.project.repos.TypeEmployeeRepo;
import ru.golfstream.project.rest.dto.TypeEmployeeDto;
import ru.golfstream.project.service.TypeEmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeEmployeeServiceImpl implements TypeEmployeeService {
    private final TypeEmployeeRepo typeEmployeeRepo;

    @Override
    public List<TypeEmployeeDto> findAll() {
        List<TypeEmployee> typeEmployee = typeEmployeeRepo.findAll();
        return typeEmployee.stream()
                .map(this::buildTypeEmployeeDto)
                .collect(Collectors.toList());
    }

    protected TypeEmployeeDto buildTypeEmployeeDto(TypeEmployee typeEmployee){
        return TypeEmployeeDto.builder()
                .rate(typeEmployee.getRate())
                .type(typeEmployee.getType())
                .build();
    }
}
