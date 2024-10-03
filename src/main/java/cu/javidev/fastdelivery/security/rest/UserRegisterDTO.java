package cu.javidev.fastdelivery.security.rest;


import cu.javidev.fastdelivery.security.exceptions.PasswordsDoNotMatch;
import jakarta.validation.constraints.NotBlank;


public record UserRegisterDTO(
        @NotBlank(message = "Field username cannot be empty or null")
        String username,

        @NotBlank(message = "Field password cannot be empty or null")
        String password,

        @NotBlank(message = "Field password_confirm cannot be empty or null")
        String password_confirm) {

    public void isPasswordsMatch() {
        if (!password.equals(password_confirm)) throw new PasswordsDoNotMatch("Passwords do not match");
    }
}

