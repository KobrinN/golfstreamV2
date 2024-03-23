package ru.golfstream.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.golfstream.project.entity.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {

}
