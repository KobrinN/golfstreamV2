package ru.golfstream.project.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Not valid USERNAME!")
    String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "PASSWORD pattern: minimum eight characters, at least one uppercase letter, one lowercase letter and one number!")
    String password;
    @Email(message = "Not valid EMAIL!")
    String mail;
}
