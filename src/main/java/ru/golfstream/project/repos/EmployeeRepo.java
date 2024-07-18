package ru.golfstream.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.golfstream.project.entity.Employee;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query(value = "from Employee e left join fetch e.routes")
    Optional<Employee> findByIdWithRoute(Long id);
}
