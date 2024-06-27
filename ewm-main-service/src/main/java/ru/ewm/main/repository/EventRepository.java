package ru.ewm.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ewm.main.model.event.Event;
import ru.ewm.main.model.event.EventState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e " +
            "FROM Event e " +
            "WHERE (COALESCE(:categoryIds) IS NULL OR e.category.id IN :categoryIds) " +
            "   AND (COALESCE(:initiatorIds) IS NULL OR e.initiator.id IN :initiatorIds) " +
            "   AND (COALESCE(:rangeBegin) IS NULL OR e.eventDate >= :rangeBegin) " +
            "   AND (COALESCE(:rangeEnd) IS NULL OR e.eventDate <= :rangeEnd) " +
            "   AND (COALESCE(:states) IS NULL OR e.state IN :states) ")
    @EntityGraph(attributePaths = {"category", "initiator", "location"})
    Page<Event> findAllByParams(
            List<Long> categoryIds,
            List<Long> initiatorIds,
            LocalDateTime rangeBegin,
            LocalDateTime rangeEnd,
            List<EventState> states,
            Pageable pageable
    );

    @Query("SELECT e " +
            "FROM Event e " +
            "WHERE e.id = :id " +
            "   AND e.initiator.id = :initiatorId ")
    @EntityGraph(attributePaths = {"category", "initiator", "location"})
    Optional<Event> findByIdAndInitiatorId(long id, long initiatorId);

    @Query("SELECT e " +
            "FROM Event e " +
            "WHERE e.initiator.id = :initiatorId ")
    @EntityGraph(attributePaths = {"category", "initiator", "location"})
    Page<Event> findAllByInitiatorId(long initiatorId, Pageable pageable);

    @Query("SELECT e " +
            "FROM Event e " +
            "WHERE ( " +
            "       COALESCE(:search) IS NULL " +
            "       OR UPPER(e.annotation) LIKE CONCAT('%',UPPER(:search),'%') " +
            "       OR UPPER(e.description) LIKE CONCAT('%',UPPER(:search),'%') " +
            "   ) " +
            "   AND (COALESCE(:categoryIds) IS NULL OR e.category.id IN :categoryIds) " +
            "   AND (COALESCE(:rangeBegin) IS NULL OR e.eventDate >= :rangeBegin) " +
            "   AND (COALESCE(:rangeEnd) IS NULL OR e.eventDate <= :rangeEnd) " +
            "   AND (COALESCE(:paid) IS NULL OR e.paid = :paid) ")
    @EntityGraph(attributePaths = {"category", "initiator", "location"})
    Page<Event> findAllByParams(
            String search,
            List<Long> categoryIds,
            LocalDateTime rangeBegin,
            LocalDateTime rangeEnd,
            Boolean paid,
            Pageable pageable
    );

    @Query("SELECT e " +
            "FROM Event e " +
            "WHERE COALESCE(:ids) IS NULL OR e.id IN :ids ")
    @EntityGraph(attributePaths = {"category", "initiator", "location"})
    List<Event> findAllByIdIn(List<Long> ids);

    boolean existsByCategoryId(long categoryId);

    boolean existsByLocationId(long locationId);

}
