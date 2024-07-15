package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends AbstractEntity{
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "secondname", nullable = false)
    private String secondname;
    @Column(name = "country")
    private String country;
    @Column(name = "opening_hours")
    private Long openingHours;
    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    private TypeEmployee type;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor")
    private List<Route> routes;
}
