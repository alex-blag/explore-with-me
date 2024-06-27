package ru.ewm.main.service.compilation;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.model.Compilation;

public interface CompilationService {

    Compilation save(Compilation compilation);

    Compilation getByIdOrThrow(long id);

    void deleteById(long id);

    Page<Compilation> findAllByPinned(Boolean pinned, Pageable pageable);

}
