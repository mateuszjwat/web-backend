package pl.edu.wat.backend.dtos;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class PasswordChangeDTO {
    @NotBlank
    @Size(min = 6, max = 40)
    String password;
}
