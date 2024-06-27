package ru.ewm.main.dto.compilation;

import lombok.Data;

import java.util.List;

@Data
public class CompilationListResponseDto {

    private List<CompilationResponseDto> compilations;

    private Long totalElements;

}
