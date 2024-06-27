package ru.ewm.main.controller.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.compilation.CompilationCreateRequestDto;
import ru.ewm.main.dto.compilation.CompilationResponseDto;
import ru.ewm.main.dto.compilation.CompilationUpdateRequestDto;
import ru.ewm.main.mapper.CompilationMapper;
import ru.ewm.main.model.Compilation;
import ru.ewm.main.service.compilation.CompilationAdminService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
public class CompilationAdminController {

    private final CompilationAdminService compilationAdminService;
    private final CompilationMapper compilationMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationResponseDto createCompilation(
            @RequestBody @Valid CompilationCreateRequestDto compilationCreateRequestDto
    ) {
        Compilation compilation = compilationAdminService.save(compilationCreateRequestDto);
        return compilationMapper.toCompilationResponseDto(compilation);
    }

    @PatchMapping("/{compilationId}")
    public CompilationResponseDto updateCompilationById(
            @PathVariable long compilationId,
            @RequestBody @Valid CompilationUpdateRequestDto compilationUpdateRequestDto
    ) {
        Compilation compilation = compilationAdminService.updateById(compilationId, compilationUpdateRequestDto);
        return compilationMapper.toCompilationResponseDto(compilation);
    }

    @DeleteMapping("/{compilationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilationById(
            @PathVariable long compilationId
    ) {
        compilationAdminService.deleteById(compilationId);
    }

}
