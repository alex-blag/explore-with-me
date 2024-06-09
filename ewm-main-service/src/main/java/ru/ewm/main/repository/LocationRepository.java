package ru.ewm.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ewm.main.model.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Page<Location> findAllByIdIn(List<Long> ids, Pageable pageable);

}
