package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client extends AbstractEntity implements UserDetails {
    @Column(name = "username", nullable = false, length = 100)
    private String username;
    @Column(name = "mail", nullable = false, length = 100)
    private String mail;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "registration_date")
    private LocalDate registration_date;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idClient")
    private List<Purchase> purchases;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "purchase",
            joinColumns = @JoinColumn(name = "id_client"),
            inverseJoinColumns = @JoinColumn(name = "id_voucher")
    )
    private List<Voucher> vouchers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
