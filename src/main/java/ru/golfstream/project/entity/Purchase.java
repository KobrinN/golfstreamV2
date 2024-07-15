package ru.golfstream.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Purchase extends AbstractEntity{
    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;
    @ManyToOne
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
