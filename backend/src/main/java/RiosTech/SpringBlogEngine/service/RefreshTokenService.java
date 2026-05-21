package RiosTech.SpringBlogEngine.service;

import RiosTech.SpringBlogEngine.entity.RefreshToken;
import RiosTech.SpringBlogEngine.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public RefreshToken create(String username) {

        RefreshToken token = new RefreshToken();

        token.setToken(UUID.randomUUID().toString());
        token.setUsername(username);
        token.setExpiryDate(LocalDateTime.now().plusDays(7));
        token.setRevoked(false);

        return repository.save(token);
    }

    public RefreshToken validate(String token) {

        RefreshToken rt = repository.findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Invalid refresh token"));

        if (rt.isRevoked() ||
                rt.getExpiryDate().isBefore(LocalDateTime.now())) {

            throw new RuntimeException("Expired refresh token");
        }

        return rt;
    }
}

