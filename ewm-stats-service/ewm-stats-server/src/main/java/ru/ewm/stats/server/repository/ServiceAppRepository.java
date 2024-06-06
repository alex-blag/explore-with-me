package ru.ewm.stats.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ewm.stats.server.model.ServiceApp;

public interface ServiceAppRepository extends JpaRepository<ServiceApp, Long> {

    ServiceApp findByName(String name);

}
