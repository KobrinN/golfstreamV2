package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "voucher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Voucher extends AbstractEntity{
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "reservation")
    private Long reservation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;
    @OneToMany(mappedBy = "voucher",cascade = CascadeType.REMOVE)
    private List<Purchase> purchases;
    @ManyToMany
    @JoinTable(
            name = "purchase",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}
