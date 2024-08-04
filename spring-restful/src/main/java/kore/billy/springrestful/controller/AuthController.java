package kore.billy.springrestful.controller;

import kore.billy.springrestful.entity.User;
import kore.billy.springrestful.model.LoginUserRequest;
import kore.billy.springrestful.model.Response;
import kore.billy.springrestful.model.TokenResponse;
import kore.billy.springrestful.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(
            path = "/api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<TokenResponse> login(@RequestBody LoginUserRequest loginUserRequest) {
        TokenResponse tokenResponse = authService.login(loginUserRequest);
        return Response.<TokenResponse>builder().data(tokenResponse).build();
    }

    @DeleteMapping(
            path = "/api/auth/logout",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> logout(User user) {
        authService.logout(user);
        return Response.<String>builder().data("OK").build();
    }
}
