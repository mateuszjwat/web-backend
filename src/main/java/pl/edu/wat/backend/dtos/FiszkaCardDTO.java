package pl.edu.wat.backend.dtos;

import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
public class FiszkaCardDTO {
    private String face;
    private String reverse;
}
