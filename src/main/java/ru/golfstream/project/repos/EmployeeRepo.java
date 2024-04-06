package ru.golfstream.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.golfstream.project.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
