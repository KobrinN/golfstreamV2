package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends AbstractEntity{
    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_voucher", nullable = false)
    private Voucher idVoucher;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client", nullable = false)
    private Client idClient;
}
