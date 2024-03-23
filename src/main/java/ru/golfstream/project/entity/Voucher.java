package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "voucher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Voucher extends AbstractEntity{
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "reservation")
    private Integer reservation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_route")
    private Route idRoute;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idVoucher",cascade = CascadeType.REMOVE)
    private List<Purchase> purchases;
}
