package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.TypeEmployee;
import ru.golfstream.project.exception.exceptions.common.InvlaidFieldException;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.TypeEmployeeRepo;
import ru.golfstream.project.rest.dto.request.TypeRequest;
import ru.golfstream.project.rest.dto.TypeEmployeeDto;
import ru.golfstream.project.service.TypeEmployeeService;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Long add(TypeRequest request) {
        if(request.getRate() <= 0){
            throw new InvlaidFieldException("Ставка не может быть <= 0!");
        }
        TypeEmployee typeEmployee = new TypeEmployee();
        typeEmployee.setType(request.getType());
        typeEmployee.setRate(request.getRate());

        typeEmployeeRepo.saveAndFlush(typeEmployee);
        return typeEmployee.getId();
    }

    @Override
    public TypeEmployeeDto delete(Long id) {
        TypeEmployee typeEmployee = proofTypeExist(id);
        typeEmployeeRepo.delete(typeEmployee);
        return buildTypeEmployeeDto(typeEmployee);
    }

    @Override
    public TypeEmployeeDto update(Long id, TypeRequest request) {
        TypeEmployee typeEmployee = proofTypeExist(id);

        typeEmployee.setType(request.getType());
        typeEmployee.setRate(request.getRate());

        return buildTypeEmployeeDto(typeEmployee);
    }

    protected TypeEmployee proofTypeExist(Long id){
        Optional<TypeEmployee> typeEmployeeFromBd = typeEmployeeRepo.findById(id);
        if(typeEmployeeFromBd.isEmpty()){
            throw new NotFoundException("Нет типа работника с ID = " + id + "!");
        }
        return typeEmployeeFromBd.get();
    }

    protected TypeEmployeeDto buildTypeEmployeeDto(TypeEmployee typeEmployee){
        return TypeEmployeeDto.builder()
                .rate(typeEmployee.getRate())
                .type(typeEmployee.getType())
                .build();
    }
}
