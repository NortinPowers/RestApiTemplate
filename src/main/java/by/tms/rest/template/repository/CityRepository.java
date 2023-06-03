package by.tms.rest.template.repository;

import by.tms.rest.template.domain.City;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findCityByName(String name);
}
