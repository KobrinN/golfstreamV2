package ru.golfstream.project.rest.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherOfClientDto{
    private String name;
    private Double price;
}
