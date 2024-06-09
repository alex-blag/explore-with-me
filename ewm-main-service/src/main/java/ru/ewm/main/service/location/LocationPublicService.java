package ru.ewm.main.service.location;

import ru.ewm.main.model.Location;

public interface LocationPublicService {

    Location getByIdOrThrow(long id);

}
