package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistrar;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.TypeEmployee;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.exception.exceptions.common.InvlaidFieldException;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.TypeEmployeeRepo;
import ru.golfstream.project.rest.dto.mapper.TypeEmployeeMapper;
import ru.golfstream.project.rest.dto.mapper.TypeEmployeeMapperImpl;
import ru.golfstream.project.rest.dto.request.TypeEmployeeRequest;
import ru.golfstream.project.rest.dto.response.TypeEmployeeResponse;
import ru.golfstream.project.service.TypeEmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeEmployeeServiceImpl implements TypeEmployeeService {
    private final TypeEmployeeRepo typeEmployeeRepo;
    private final TypeEmployeeMapper typeMapper = new TypeEmployeeMapperImpl();

    @Override
    public TypeEmployeeResponse getById(Long id) {
        TypeEmployee typeEmployee = checkExistTypeAndGet(id);
        return typeMapper.toResponse(typeEmployee);
    }

    @Override
    public List<TypeEmployeeResponse> getAll() {
        List<TypeEmployee> typeEmployee = typeEmployeeRepo.findAll();
        return typeEmployee.stream()
                .map(typeMapper::toResponse)
                .toList();
    }

    @Override
    public Long post(TypeEmployeeRequest request) {
        TypeEmployee typeEmployee = typeMapper.toModel(request);
        typeEmployeeRepo.saveAndFlush(typeEmployee);
        return typeEmployee.getId();
    }

    @Override
    public void delete(Long id) {
        Optional<TypeEmployee> type = typeEmployeeRepo.findById(id);
        type.ifPresent(typeEmployeeRepo::delete);
    }

    @Override
    public TypeEmployeeResponse edit(Long id, TypeEmployeeRequest request) {
        TypeEmployee typeEmployee = checkExistTypeAndGet(id);
        typeEmployee = typeMapper.toModel(request);
        typeEmployeeRepo.save(typeEmployee);
        return typeMapper.toResponse(typeEmployee);
    }

    private TypeEmployee checkExistTypeAndGet(Long id) throws NotFoundException{
        Optional<TypeEmployee> type = typeEmployeeRepo.findById(id);
        if (type.isEmpty()) {
            throw new NotFoundException("Not found TYPE_OF_EMPLOYEE with id: " + id + "!");
        }
        return type.get();
    }
}
