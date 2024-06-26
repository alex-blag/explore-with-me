package ru.ewm.main.service.compilation;


import ru.ewm.main.model.Compilation;

public interface CompilationService {

    Compilation save(Compilation compilation);

    Compilation getByIdOrThrow(long id);

    void deleteById(long id);

}
