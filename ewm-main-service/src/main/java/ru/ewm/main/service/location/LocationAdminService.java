package ru.ewm.main.service.location;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.dto.location.LocationCreateRequestDto;
import ru.ewm.main.dto.location.LocationUpdateRequestDto;
import ru.ewm.main.model.Location;

import java.util.List;

public interface LocationAdminService {

    Location save(LocationCreateRequestDto locationCreateRequestDto);

    Location updateById(long id, LocationUpdateRequestDto locationUpdateRequestDto);

    Location getByIdOrThrow(long id);

    Page<Location> findAllByIds(List<Long> ids, Pageable pageable);

    void deleteById(long id);

}
