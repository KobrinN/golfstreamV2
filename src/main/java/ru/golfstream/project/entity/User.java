package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(name = "user-role-graph", attributeNodes = @NamedAttributeNode("roles")),
        @NamedEntityGraph(name = "user-voucher-graph", attributeNodes = @NamedAttributeNode("vouchers")),
        @NamedEntityGraph(name = "user-purchase-graph", attributeNodes = @NamedAttributeNode("purchases"))
})
public class User extends AbstractEntity{
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    @CollectionTable(name = "role")
    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "role")
    private Set<Role> roles;
    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases;
    @ManyToMany
    @JoinTable(
            name = "purchase",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "voucher_id")
    )
    private List<Voucher> vouchers;
}
