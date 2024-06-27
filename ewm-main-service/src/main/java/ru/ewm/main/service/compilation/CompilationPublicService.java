package ru.ewm.main.service.compilation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.model.Compilation;

public interface CompilationPublicService {

    Compilation getByIdOrThrow(long id);

    Page<Compilation> findAllByPinned(Boolean pinned, Pageable pageable);

}
