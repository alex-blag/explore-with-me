package ru.ewm.main.dto.compilation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class CompilationCreateRequestDto {

    private Boolean pinned;

    @NotBlank
    @Size(max = 120)
    private String title;

    private List<Long> eventIds;

}
