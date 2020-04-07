package com.polinashlepakova.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    @NotEmpty(message = "Login can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9]+$",
            message = "Login must have only latin letters and digits")
    private String login;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 8, max = 20,
            message = "Password must be between 8 and 20 characters")
    private String password;
}
