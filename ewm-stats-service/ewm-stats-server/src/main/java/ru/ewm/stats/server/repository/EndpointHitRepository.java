package ru.ewm.stats.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ewm.stats.server.model.EndpointHit;
import ru.ewm.stats.server.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointHitRepository extends JpaRepository<EndpointHit, Long> {

    @Query(
            "SELECT " +
                    "   sa.name AS app, " +
                    "   eh.uri AS uri, " +
                    "   COUNT(*) AS hits " +
                    "FROM " +
                    "   EndpointHit AS eh " +
                    "LEFT JOIN " +
                    "   eh.serviceApp AS sa " +
                    "WHERE " +
                    "   eh.timestamp BETWEEN :begin AND :end " +
                    "GROUP BY " +
                    "   eh.uri, " +
                    "   sa.name "
    )
    List<ViewStats> findAllByTimestampBetween(LocalDateTime begin, LocalDateTime end);

    @Query(
            "SELECT " +
                    "   sa.name AS app, " +
                    "   eh.uri AS uri, " +
                    "   COUNT(*) AS hits " +
                    "FROM " +
                    "   EndpointHit AS eh " +
                    "LEFT JOIN " +
                    "   eh.serviceApp AS sa " +
                    "WHERE " +
                    "   eh.timestamp BETWEEN :begin AND :end " +
                    "   AND " +
                    "       eh.uri IN :uris " +
                    "GROUP BY " +
                    "   eh.uri, " +
                    "   sa.name "
    )
    List<ViewStats> findAllByTimestampBetweenAndUriIn(LocalDateTime begin, LocalDateTime end, List<String> uris);

    @Query(
            "SELECT " +
                    "   sa.name AS app, " +
                    "   eh.uri AS uri, " +
                    "   COUNT(DISTINCT eh.ip) AS hits " +
                    "FROM " +
                    "   EndpointHit AS eh " +
                    "LEFT JOIN " +
                    "   eh.serviceApp AS sa " +
                    "WHERE " +
                    "   eh.timestamp BETWEEN :begin AND :end " +
                    "GROUP BY " +
                    "   eh.uri, " +
                    "   sa.name "
    )
    List<ViewStats> findAllDistinctIpByTimestampBetween(LocalDateTime begin, LocalDateTime end);

    @Query(
            "SELECT " +
                    "   sa.name AS app, " +
                    "   eh.uri AS uri, " +
                    "   COUNT(DISTINCT eh.ip) AS hits " +
                    "FROM " +
                    "   EndpointHit AS eh " +
                    "LEFT JOIN " +
                    "   eh.serviceApp AS sa " +
                    "WHERE " +
                    "   eh.timestamp BETWEEN :begin AND :end " +
                    "   AND " +
                    "       eh.uri IN :uris " +
                    "GROUP BY " +
                    "   eh.uri, " +
                    "   sa.name "
    )
    List<ViewStats> findAllDistinctIpByTimestampBetweenAndUriIn(
            LocalDateTime begin, LocalDateTime end, List<String> uris
    );

}
