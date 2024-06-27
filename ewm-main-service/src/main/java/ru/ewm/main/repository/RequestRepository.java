package ru.ewm.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ewm.main.model.request.Request;
import ru.ewm.main.model.request.RequestStatus;
import ru.ewm.main.model.request.RequestTuple;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    boolean existsByEventIdAndRequesterId(long eventId, long requesterId);

    long countAllByEventIdAndStatus(long eventId, RequestStatus requestStatus);

    @Query("SELECT r " +
            "FROM Request r " +
            "WHERE r.requester.id = :requesterId ")
    @EntityGraph(attributePaths = {"event", "requester"})
    Page<Request> findAllByRequesterId(long requesterId, Pageable pageable);

    @Query("SELECT " +
            "   r.event.id AS eventId, " +
            "   COUNT(*) AS numberOfConfirmedRequests " +
            "FROM " +
            "   Request AS r " +
            "WHERE " +
            "   r.status = 'CONFIRMED' " +
            "   AND COALESCE(:eventIds) IS NOT NULL AND r.event.id IN :eventIds " +
            "GROUP BY " +
            "   r.event.id ")
    List<RequestTuple> countAllConfirmedRequestsByEventIdIn(List<Long> eventIds);

}
