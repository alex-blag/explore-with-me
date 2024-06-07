package ru.ewm.stats.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ewm.stats.common.dto.EndpointHitRequestDto;
import ru.ewm.stats.common.dto.EndpointHitResponseDto;
import ru.ewm.stats.common.dto.ViewStatsResponseDto;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatsClientImpl implements StatsClient {

    public static final String HIT_ENDPOINT = "/hit";
    public static final String STATS_ENDPOINT = "/stats";

    private final RestTemplate restTemplate;

    @Autowired
    public StatsClientImpl(
            @Value("${ewm.stats.server.url}") String statsServerUrl, RestTemplateBuilder restTemplateBuilder
    ) {
        this.restTemplate = restTemplateBuilder
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .uriTemplateHandler(new DefaultUriBuilderFactory(statsServerUrl))
                .build();
    }

    @Override
    public EndpointHitResponseDto saveHit(EndpointHitRequestDto endpointHitRequestDto) {
        return restTemplate.postForObject(HIT_ENDPOINT, endpointHitRequestDto, EndpointHitResponseDto.class);
    }

    @Override
    public List<ViewStatsResponseDto> getStats(
            LocalDateTime begin, LocalDateTime end, List<String> uris, boolean unique
    ) {
        URI uri = UriComponentsBuilder
                .fromPath(STATS_ENDPOINT)
                .queryParam("begin", begin)
                .queryParam("end", end)
                .queryParam("uris", uris)
                .queryParam("unique", unique)
                .encode()
                .build()
                .toUri();

        ViewStatsResponseDto[] viewStats = restTemplate.getForObject(uri, ViewStatsResponseDto[].class);

        return viewStats == null
                ? List.of()
                : List.of(viewStats);
    }

}
