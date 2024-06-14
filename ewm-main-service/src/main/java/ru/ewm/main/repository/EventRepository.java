package ru.ewm.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ewm.main.model.Event;
import ru.ewm.main.model.State;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e " +
            "FROM Event e " +
            "WHERE (COALESCE(:categoryIds) IS NULL OR c.id IN :categoryIds) " +
            "   AND (COALESCE(:initiatorIds) IS NULL OR i.id IN :initiatorIds) " +
            "   AND (COALESCE(:rangeBegin) IS NULL OR e.eventDate >= :rangeBegin) " +
            "   AND (COALESCE(:rangeEnd) IS NULL OR e.eventDate <= :rangeEnd) " +
            "   AND (COALESCE(:states) IS NULL OR e.state IN :states) ")
    @EntityGraph(attributePaths = {"category", "initiator", "location"})
    Page<Event> findAllByParams(
            List<Long> categoryIds,
            List<Long> initiatorIds,
            LocalDateTime rangeBegin,
            LocalDateTime rangeEnd,
            List<State> states,
            Pageable pageable
    );

    @Query("SELECT e " +
            "FROM Event e " +
            "WHERE e.id = :id " +
            "   AND e.initiator.id = :initiatorId ")
    @EntityGraph(attributePaths = {"category", "initiator", "location"})
    Optional<Event> findByIdAndInitiatorId(long id, long initiatorId);

}
