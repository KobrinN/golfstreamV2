package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client extends AbstractEntity{
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;
    @Column(name = "secondname", nullable = false, length = 100)
    private String secondname;
    @Column(name = "mail", nullable = false, length = 100)
    private String mail;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "registration_date")
    private LocalDate registration_date;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idClient")
    private List<Purchase> purchases;
}
