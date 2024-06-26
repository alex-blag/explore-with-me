package ru.ewm.main.dto.compilation;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ewm.main.dto.event.EventBriefResponseDto;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class CompilationResponseDto {

    private Long id;

    private List<EventBriefResponseDto> events;

    private Boolean pinned;

    @Size(max = 120)
    private String title;

}
