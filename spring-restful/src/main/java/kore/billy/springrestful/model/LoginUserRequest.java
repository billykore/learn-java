package kore.billy.springrestful.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserRequest {
    @NotBlank(message = "username is required")
    @Size(max = 100)
    private String username;

    @NotBlank(message = "password is required")
    @Size(max = 100)
    private String password;
}
