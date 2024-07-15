package ru.golfstream.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.entity.Voucher;

import java.util.List;

@Repository
public interface VoucherRepo extends JpaRepository<Voucher, Long> {

    List<Voucher> findByRoute(Route idRoute);
}
