package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "route")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Route extends AbstractEntity{
    @Column(name = "from_where", length = 100)
    private String fromWhere;
    @Column(name = "to_where", length = 100)
    private String toWhere;
    @Column(name = "departure")
    private LocalDate departure;
    @Column(name = "arrival")
    private LocalDate arrival;
    @Column(name = "transportation", length = 100)
    private String transportation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_instructor")
    private Employee idInstructor;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idRoute", cascade = CascadeType.ALL)
    private List<Voucher> vouchers;
}
