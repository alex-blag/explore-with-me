package ru.ewm.main.service.location;

import ru.ewm.main.model.Location;

public interface LocationPrivateService {

    Location getByIdOrThrow(long id);

}
