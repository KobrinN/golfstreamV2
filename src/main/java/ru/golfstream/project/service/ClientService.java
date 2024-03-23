package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.VoucherOfClientDto;
import ru.golfstream.project.rest.dto.ClientDto;
import ru.golfstream.project.rest.dto.NewClientRequest;
import ru.golfstream.project.rest.dto.VoucherOfClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAllClients();

    Integer addNewClient(NewClientRequest request);

    ClientDto findById(Integer id);

    void deleteById(Integer id);

    ClientDto edit(Integer id, ClientDto clientDto);

    List<VoucherOfClientDto> findVouchersOfClient(Integer id);
}
