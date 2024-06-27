package ru.ewm.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.compilation.CompilationListResponseDto;
import ru.ewm.main.dto.compilation.CompilationResponseDto;
import ru.ewm.main.mapper.CompilationMapper;
import ru.ewm.main.model.Compilation;
import ru.ewm.main.service.compilation.CompilationPublicService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
@Validated
public class CompilationPublicController {

    private final CompilationPublicService compilationPublicService;
    private final CompilationMapper compilationMapper;

    @GetMapping("/{compilationId}")
    public CompilationResponseDto getCompilationById(
            @PathVariable long compilationId
    ) {
        Compilation compilation = compilationPublicService.getByIdOrThrow(compilationId);
        return compilationMapper.toCompilationResponseDto(compilation);
    }

    @GetMapping
    public CompilationListResponseDto getAllCompilationsByPinned(
            @RequestParam(required = false) Boolean pinned,
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Compilation> compilations = compilationPublicService.findAllByPinned(pinned, pageable);
        return compilationMapper.toCompilationListResponseDto(compilations.getContent(), compilations.getTotalElements());
    }

}
