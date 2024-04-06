package ru.golfstream.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.golfstream.project.entity.Client;
import ru.golfstream.project.rest.dto.VoucherOfClientDto;
import ru.golfstream.project.rest.dto.VoucherOfClientDto;

import java.util.List;
@Repository
public interface ClientRepo  extends JpaRepository<Client, Long> {
    @Query(value = "select v.name as nameofvoucher, v.price as price " +
            "from purchase p " +
            "join client c " +
            "on p.id_client = c.id " +
            "join voucher v " +
            "on p.id_voucher = v.id " +
            "where c.id = :id;", nativeQuery = true)
    List<VoucherOfClientDto> getClientAndVoucherDto(@Param("id") Long id);
}
