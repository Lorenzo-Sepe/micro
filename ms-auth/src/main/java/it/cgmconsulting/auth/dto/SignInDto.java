package it.cgmconsulting.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignInDto(
        @NotBlank(message = "Username")
        String username,

        @NotBlank(message = "Password cannot be null or blank")
        @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$£%^&+=!]).*$",
                message = "Password must contain at least one digit, one lowercase, one uppercase letter and one special character")

        String password
) {
}
