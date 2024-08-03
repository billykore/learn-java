package kore.billy.springrestful.service;

import jakarta.transaction.Transactional;
import kore.billy.springrestful.entity.User;
import kore.billy.springrestful.model.LoginUserRequest;
import kore.billy.springrestful.model.TokenResponse;
import kore.billy.springrestful.repo.UserRepo;
import kore.billy.springrestful.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ValidationService validator;

    public TokenResponse login(LoginUserRequest request) {
        validator.validate(request);

        User user = userRepo.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }

        user.setToken(UUID.randomUUID().toString());
        user.setTokenExpiredAt(next30Days());
        userRepo.save(user);

        return TokenResponse.builder()
                .token(user.getToken())
                .expiresIn(user.getTokenExpiredAt())
                .build();
    }

    private Long next30Days() {
        return System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000;
    }

    @Transactional
    public void logout(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);
        userRepo.save(user);
    }
}
