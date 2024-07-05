package ru.ewm.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ewm.main.model.Location;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l " +
            "FROM Location l " +
            "WHERE COALESCE(:ids) IS NULL OR l.id IN :ids ")
    Page<Location> findAllByIdIn(List<Long> ids, Pageable pageable);

}
