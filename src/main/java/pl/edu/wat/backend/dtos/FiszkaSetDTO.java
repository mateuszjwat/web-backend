package pl.edu.wat.backend.dtos;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
public class FiszkaSetDTO {
    private String title;
    private String description;
    private Set<FiszkaCardDTO> cards;
}
