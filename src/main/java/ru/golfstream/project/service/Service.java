package ru.golfstream.project.service;

import java.util.List;

public interface Service <Request, Response> {
    Response getById(Long id);
    List<Response> getAll();
    Long post(Request request);
    Response edit(Long id, Request request);
    void delete(Long id);

}
