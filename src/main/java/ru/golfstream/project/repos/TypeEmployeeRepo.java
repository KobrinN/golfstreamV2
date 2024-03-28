package ru.golfstream.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.golfstream.project.entity.TypeEmployee;
@Repository
public interface TypeEmployeeRepo extends JpaRepository<TypeEmployee, Integer> {
}
