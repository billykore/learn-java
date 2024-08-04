package kore.billy.springrestful.service;

import jakarta.transaction.Transactional;
import kore.billy.springrestful.entity.User;
import kore.billy.springrestful.model.RegisterUserRequest;
import kore.billy.springrestful.model.UpdateUserRequest;
import kore.billy.springrestful.model.UserResponse;
import kore.billy.springrestful.repo.UserRepo;
import kore.billy.springrestful.security.BCrypt;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class UserService {
    private final UserRepo userRepo;

    private final ValidationService validator;

    @Autowired
    public UserService(UserRepo userRepo, ValidationService validator) {
        this.userRepo = userRepo;
        this.validator = validator;
    }

    @Transactional
    public void register(RegisterUserRequest request) {
        validator.validate(request);

        if (userRepo.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepo.save(user);
    }

    public UserResponse get(User user) {
        return buildUserResponse(user);
    }

    public UserResponse update(User user, UpdateUserRequest request) {
        validator.validate(user);

        if (Objects.nonNull(request.getName())) {
            user.setName(request.getName());
        }
        if (Objects.nonNull(request.getPassword())) {
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        userRepo.save(user);

        return buildUserResponse(user);
    }

    private UserResponse buildUserResponse(@NotNull User user) {
        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
