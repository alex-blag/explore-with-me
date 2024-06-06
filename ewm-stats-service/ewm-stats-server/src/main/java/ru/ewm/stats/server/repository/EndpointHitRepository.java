package ru.ewm.stats.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ewm.stats.server.model.EndpointHit;

public interface EndpointHitRepository extends JpaRepository<EndpointHit, Long> {
/*
    @Query(
            "SELECT sa.name AS app, " +
                    "   eh.uri AS uri, " +
                    "   COUNT(DISTINCT eh.ip4) AS hits " +
                    "FROM EndpointHit eh " +
                    "LEFT JOIN eh.serviceApp sa " +
                    "WHERE eh.timestamp BETWEEN :start AND :end " +
                    "GROUP BY sa.name, eh.uri"
    )
    List<ViewStats> findAllDistinctIp4ByTimestampBetween(
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );

    @Query(
            "SELECT sa.name AS app, " +
                    "   eh.uri AS uri, " +
                    "   COUNT(eh.ip4) AS hits " +
                    "FROM EndpointHit eh " +
                    "LEFT JOIN eh.serviceApp sa " +
                    "WHERE eh.timestamp BETWEEN :start AND :end " +
                    "GROUP BY sa.name, eh.uri"
    )
    List<ViewStats> findAllByTimestampBetween(
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );

    @Query(
            "SELECT sa.name AS app, " +
                    "   eh.uri AS uri, " +
                    "   COUNT(DISTINCT eh.ip4) AS hits " +
                    "FROM EndpointHit eh " +
                    "LEFT JOIN eh.serviceApp sa " +
                    "WHERE eh.timestamp BETWEEN :start AND :end " +
                    "   AND eh.uri IN :uris " +
                    "GROUP BY sa.name, eh.uri"
    )
    List<ViewStats> findAllDistinctIp4ByTimestampBetweenAndUriIn(
            LocalDateTime start,
            LocalDateTime end,
            List<String> uris,
            Pageable pageable
    );

    @Query(
            "SELECT sa.name AS app, " +
                    "   eh.uri AS uri, " +
                    "   COUNT(eh.ip4) AS hits " +
                    "FROM EndpointHit eh " +
                    "LEFT JOIN eh.serviceApp sa " +
                    "WHERE eh.timestamp BETWEEN :start AND :end " +
                    "   AND eh.uri IN :uris " +
                    "GROUP BY sa.name, eh.uri"
    )
    List<ViewStats> findAllByTimestampBetweenAndUriIn(
            LocalDateTime start,
            LocalDateTime end,
            List<String> uris,
            Pageable pageable
    );
*/
}
