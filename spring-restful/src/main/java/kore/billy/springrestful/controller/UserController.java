package kore.billy.springrestful.controller;

import kore.billy.springrestful.entity.User;
import kore.billy.springrestful.model.RegisterUserRequest;
import kore.billy.springrestful.model.Response;
import kore.billy.springrestful.model.UpdateUserRequest;
import kore.billy.springrestful.model.UserResponse;
import kore.billy.springrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> register(@RequestBody RegisterUserRequest request) {
        userService.register(request);
        return Response.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<UserResponse> get(User user) {
        UserResponse userResponse = userService.get(user);
        return Response.<UserResponse>builder().data(userResponse).build();
    }

    @PatchMapping(
            path = "/api/users/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<UserResponse> update(User user, @RequestBody UpdateUserRequest request) {
        UserResponse userResponse = userService.update(user, request);
        return Response.<UserResponse>builder().data(userResponse).build();
    }
}
