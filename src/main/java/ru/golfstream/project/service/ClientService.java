package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.VoucherOfClientDto;
import ru.golfstream.project.rest.dto.ClientDto;
import ru.golfstream.project.rest.dto.request.ClientRequest;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAllClients();

    Long addNewClient(ClientRequest request);

    ClientDto findById(Long id);

    void deleteById(Long id);

    ClientDto edit(Long id, ClientDto clientDto);

}
