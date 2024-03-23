package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "type_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeEmployee extends AbstractEntity{
    @Column(name = "type", nullable = false, length = 100)
    private String type;
    @Column(name = "rate", nullable = false)
    private Double rate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "type")
    private List<Employee> employees;
}
