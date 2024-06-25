package ru.ewm.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ewm.main.model.request.Request;
import ru.ewm.main.model.request.RequestStatus;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    boolean existsByEventIdAndRequesterId(long eventId, long requesterId);

    long countAllByEventIdAndStatus(long eventId, RequestStatus requestStatus);

    @Query("SELECT r " +
            "FROM Request r " +
            "WHERE r.requester.id = :requesterId ")
    @EntityGraph(attributePaths = {"event", "requester"})
    Page<Request> findAllByRequesterId(long requesterId, Pageable pageable);

}
