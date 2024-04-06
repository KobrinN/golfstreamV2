package ru.golfstream.project.repos;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.entity.Voucher;

import java.util.List;

@Repository
public interface VoucherRepo extends JpaRepository<Voucher, Long> {

    List<Voucher> findByIdRoute(Route idRoute);
}
