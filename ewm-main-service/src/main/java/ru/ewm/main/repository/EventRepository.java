package ru.ewm.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ewm.main.model.Event;
import ru.ewm.main.model.State;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(
            "SELECT " +
                    "   e " +
                    "FROM " +
                    "   Event e " +
                    "LEFT JOIN " +
                    "   e.initiator i " +
                    "LEFT JOIN " +
                    "   e.category c " +
                    "WHERE " +
                    "   (COALESCE(:initiatorIds) IS NULL OR i.id IN :initiatorIds) " +
                    "   AND (COALESCE(:states) IS NULL OR e.state IN :states) " +
                    "   AND (COALESCE(:categoryIds) IS NULL OR c.id IN :categoryIds) " +
                    "   AND (COALESCE(:rangeBegin) IS NULL OR e.eventDate >= :rangeBegin) " +
                    "   AND (COALESCE(:rangeEnd) IS NULL OR e.eventDate <= :rangeEnd)"
    )
    Page<Event> findAllByParams(
            List<Long> initiatorIds,
            List<State> states,
            List<Long> categoryIds,
            LocalDateTime rangeBegin,
            LocalDateTime rangeEnd,
            Pageable pageable
    );

}
