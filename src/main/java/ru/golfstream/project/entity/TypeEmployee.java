package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "type_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class TypeEmployee extends AbstractEntity{
    @Column(name = "type", nullable = false, length = 100)
    private String type;
    @Column(name = "rate", nullable = false)
    private Double rate;
}
