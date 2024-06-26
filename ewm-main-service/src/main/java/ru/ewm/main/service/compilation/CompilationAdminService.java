package ru.ewm.main.service.compilation;

import ru.ewm.main.dto.compilation.CompilationCreateRequestDto;
import ru.ewm.main.dto.compilation.CompilationUpdateRequestDto;
import ru.ewm.main.model.Compilation;

public interface CompilationAdminService {

    Compilation save(CompilationCreateRequestDto compilationCreateRequestDto);

    Compilation updateById(long id, CompilationUpdateRequestDto compilationUpdateRequestDto);

    void deleteById(long id);

}
