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
@NamedEntityGraphs({
        @NamedEntityGraph(name = "route-employee-graph", attributeNodes = @NamedAttributeNode("instructor")),
        @NamedEntityGraph(name = "route-vouchers-graph", attributeNodes = @NamedAttributeNode("vouchers"))
})
public class Route extends AbstractEntity{
    @Column(name = "from_where")
    private String fromWhere;
    @Column(name = "to_where")
    private String toWhere;
    @Column(name = "departure")
    private LocalDate departure;
    @Column(name = "arrival")
    private LocalDate arrival;
    @Column(name = "transportation")
    private String transportation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Employee instructor;
    @OneToMany(mappedBy = "route", cascade = CascadeType.REMOVE)
    private List<Voucher> vouchers;
}
