package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends AbstractEntity{
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;
    @Column(name = "secondname", nullable = false, length = 100)
    private String secondname;
    @Column(name = "country")
    private String country;
    @Column(name = "opening_hours")
    private Long openingHours;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type", nullable = false)
    private TypeEmployee type;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idInstructor")
    private List<Route> routes;
}
