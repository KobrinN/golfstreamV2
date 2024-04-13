package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.golfstream.project.entity.Client;
import ru.golfstream.project.exception.exceptions.client.NotFoundVoucherOfThisClient;
import ru.golfstream.project.exception.exceptions.common.EmptyFieldsException;
import ru.golfstream.project.exception.exceptions.common.InvlaidFieldException;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.ClientRepo;
import ru.golfstream.project.rest.dto.ClientDto;
import ru.golfstream.project.rest.dto.request.ClientRequest;
import ru.golfstream.project.rest.dto.VoucherOfClientDto;
import ru.golfstream.project.service.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;

    @Override
    public List<ClientDto> findAllClients() {
        List<Client> all = clientRepo.findAll();
        return all.stream()
                .map(ClientServiceImpl::buildClientDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long addNewClient(ClientRequest request) {
        Pattern pattern = Pattern.compile("^[A-Z0-9+_.-]+@[A-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(request.getMail());

        if (!StringUtils.hasText(request.getUsername()) ||
                StringUtils.hasText(request.getMail()) ||
                StringUtils.hasText(request.getPassword()) ||
                matcher.find()) {
            throw new InvlaidFieldException("Поля некорректные!");
        }

        Client client = new Client();
        client.setUsername(request.getUsername());
        client.setMail(request.getMail());
        client.setPassword(request.getPassword());

        clientRepo.saveAndFlush(client);

        return client.getId();
    }

    @Override
    public ClientDto findById(Long id) {
        Client client = proofClient(id);
        return buildClientDto(client);
    }

    @Override
    public void deleteById(Long id) {
        Client client = proofClient(id);
        clientRepo.deleteById(client.getId());
    }

    @Override
    public ClientDto edit(Long id, ClientDto clientDto) {
        Client client = proofClient(id);

        client.setUsername(clientDto.getUsername());
        client.setMail(clientDto.getMail());

        clientRepo.saveAndFlush(client);

        return buildClientDto(client);
    }

    protected static ClientDto buildClientDto(Client client) {
        return ClientDto.builder()
                .username(client.getUsername())
                .mail(client.getMail())
                .build();
    }

    private Client proofClient(Long id) throws NotFoundException{
        Optional<Client> clientFromBd = clientRepo.findById(id);
        if (clientFromBd.isEmpty()) {
            throw new NotFoundException("Не найден пользователь с ID = " + id + "!");
        }
        return clientFromBd.get();
    }


}
