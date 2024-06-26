package ru.ewm.main.dto.compilation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class CompilationUpdateRequestDto {

    private List<Long> eventIds;

    private Boolean pinned;

    @Size(max = 120)
    private String title;

}
