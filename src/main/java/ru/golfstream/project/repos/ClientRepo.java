package ru.golfstream.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.entity.Voucher;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo  extends JpaRepository<User, Long> {
    @Query(value = "select v.name as name, v.price as price " +
            "from purchase p " +
            "join client c " +
            "on p.user_id = c.id " +
            "join voucher v " +
            "on p.voucher_id = v.id " +
            "where c.id = :id", nativeQuery = true)
    List<Voucher> getVoucherOfClientDto(@Param("id") Long id);

    Optional<User> findByUsername(String username);
}

