package ru.ewm.stats.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.stats.dto.EndpointHitRequestDto;
import ru.ewm.stats.server.mapper.StatsMapper;
import ru.ewm.stats.server.model.EndpointHit;
import ru.ewm.stats.server.model.ServiceApp;
import ru.ewm.stats.server.model.ViewStats;
import ru.ewm.stats.server.repository.EndpointHitRepository;
import ru.ewm.stats.server.repository.ServiceAppRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final EndpointHitRepository endpointHitRepository;
    private final ServiceAppRepository serviceAppRepository;
    private final StatsMapper statsMapper;

    @Override
    @Transactional
    public EndpointHit save(EndpointHitRequestDto endpointHitRequestDto) {
        ServiceApp serviceApp = getServiceApp(endpointHitRequestDto.getApp());
        EndpointHit endpointHit = statsMapper.toEndpointHit(endpointHitRequestDto, serviceApp);
        return endpointHitRepository.save(endpointHit);
    }

    @Override
    public List<ViewStats> findAll(LocalDateTime begin, LocalDateTime end, List<String> uris, boolean unique) {
        List<ViewStats> viewStats;

        if (!uris.isEmpty() && unique) {
            viewStats = endpointHitRepository.findAllDistinctIp4ByTimestampBetweenAndUriIn(begin, end, uris);

        } else if (!uris.isEmpty()) {
            viewStats = endpointHitRepository.findAllByTimestampBetweenAndUriIn(begin, end, uris);

        } else if (unique) {
            viewStats = endpointHitRepository.findAllDistinctIp4ByTimestampBetween(begin, end);

        } else {
            viewStats = endpointHitRepository.findAllByTimestampBetween(begin, end);
        }

        return viewStats;
    }

    @Transactional
    private ServiceApp getServiceApp(String app) {
        ServiceApp serviceApp = serviceAppRepository.findByName(app);
        if (serviceApp == null) {
            serviceApp = serviceAppRepository.save(new ServiceApp(app));
        }
        return serviceApp;
    }

}
