package airports;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByIdentifier(String identifier);
    Airport findByName(String name);
}
