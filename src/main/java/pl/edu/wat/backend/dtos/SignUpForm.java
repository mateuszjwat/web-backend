package pl.edu.wat.backend.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

public class SignUpForm {
    @NotBlank(message = "lol")
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    //regex for only letters and digits - this will be serving as name as folder name
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Invalid Input")
    @Size(min = 3, max = 20)
    private String username;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
