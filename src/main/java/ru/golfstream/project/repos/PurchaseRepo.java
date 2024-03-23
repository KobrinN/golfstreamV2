package ru.golfstream.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.golfstream.project.entity.Purchase;
import ru.golfstream.project.entity.Voucher;

import java.util.List;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByIdVoucher(Voucher idVoucher);
}
