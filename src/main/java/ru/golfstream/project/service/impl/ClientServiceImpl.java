package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.golfstream.project.entity.Client;
import ru.golfstream.project.exception.exceptions.common.EmptyFieldsException;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.exception.exceptions.client.NotFoundVoucherOfThisClient;
import ru.golfstream.project.repos.ClientRepo;
import ru.golfstream.project.rest.dto.VoucherOfClientDto;
import ru.golfstream.project.rest.dto.ClientDto;
import ru.golfstream.project.rest.dto.NewClientRequest;
import ru.golfstream.project.service.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;

    @Override
    public List<ClientDto> findAllClients() {
        List<Client> all = clientRepo.findAll();
        return all.stream()
                .map(client -> ClientDto.builder()
                        .name(client.getName())
                        .surname(client.getSurname())
                        .secondName(client.getSecondname())
                        .mail(client.getMail())
                        .build())
                .toList();
    }

    @Override
    public Integer addNewClient(NewClientRequest request) {
        Pattern pattern = Pattern.compile("^[A-Z0-9+_.-]+@[A-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(request.getMail());

        if (!StringUtils.hasText(request.getName()) ||
                !StringUtils.hasText(request.getSurname()) ||
                !StringUtils.hasText(request.getSecondName()) ||
                !StringUtils.hasText(request.getMail()) ||
                !StringUtils.hasText(request.getPassword())) {
            throw new EmptyFieldsException("Поля некорректные!");
        }

        Client client = new Client();
        client.setName(request.getName());
        client.setSurname(request.getSurname());
        client.setSecondname(request.getSecondName());
        client.setMail(request.getMail());
        client.setPassword(request.getPassword());

        clientRepo.saveAndFlush(client);

        return client.getId();
    }

    @Override
    public ClientDto findById(Integer id) {
        Optional<Client> clientFromBd = clientRepo.findById(id);

        if (clientFromBd.isEmpty()) {
            throw new NotFoundException("Не найден пользователь с ID = " + id + "!");
        }

        Client client = clientFromBd.get();
        return buildClientDto(client);
    }

    @Override
    public void deleteById(Integer id) {
        if (!clientRepo.existsById(id)) {
            throw new NotFoundException("Не найден пользователь с ID = " + id + "!");
        }
        clientRepo.deleteById(id);
    }

    @Override
    public ClientDto edit(Integer id, ClientDto clientDto) {
        Optional<Client> clientFromBd = clientRepo.findById(id);

        if (clientFromBd.isEmpty()) {
            throw new NotFoundException("Не найден пользователь с ID = " + id + "!");
        }

        Client client = clientFromBd.get();

        client.setName(clientDto.getName());
        client.setSurname(clientDto.getSurname());
        client.setSecondname(client.getSecondname());
        client.setMail(clientDto.getMail());

        clientRepo.saveAndFlush(client);

        return buildClientDto(client);
    }

    @Override
    public List<VoucherOfClientDto> findVouchersOfClient(Integer id) {
        Optional<Client> clientFromBd = clientRepo.findById(id);

        if (clientFromBd.isEmpty()) {
            throw new NotFoundException("Не найден пользователь с ID = " + id + "!");
        }

        List<VoucherOfClientDto> clientAndVoucherDtoList = clientRepo.getClientAndVoucherDto(id);

        if(clientAndVoucherDtoList.isEmpty()){
            throw new NotFoundVoucherOfThisClient("Не найдены путёвки этого пользователя!");
        }

        return clientAndVoucherDtoList;
    }

    protected static ClientDto buildClientDto(Client client) {

        return ClientDto.builder()
                .name(client.getName())
                .surname(client.getSurname())
                .secondName(client.getSecondname())
                .mail(client.getMail())
                .build();
    }


}
