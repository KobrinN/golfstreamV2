package ru.golfstream.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.golfstream.project.entity.Client;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.rest.dto.VoucherOfClientDto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo  extends JpaRepository<Client, Long> {
    @Query(value = "select v.name as name, v.price as price " +
            "from purchase p " +
            "join client c " +
            "on p.id_client = c.id " +
            "join voucher v " +
            "on p.id_voucher = v.id " +
            "where c.id = :id", nativeQuery = true)
    List<Voucher> getVoucherOfClientDto(@Param("id") Long id);

    Optional<Client> findByUsername(String username);
}

