package RiosTech.SpringBlogEngine.controller;

import RiosTech.SpringBlogEngine.dto.LoginRequest;
import RiosTech.SpringBlogEngine.entity.RefreshToken;
import RiosTech.SpringBlogEngine.service.JwtService;
import RiosTech.SpringBlogEngine.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.cloudinary.AccessControlRule.AccessType.token;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String access = jwtService.generateToken(request.getUsername());

        RefreshToken refresh =
                refreshTokenService.create(request.getUsername());

        return ResponseEntity.ok(
                Map.of(
                        "token", token
                )
        );
    }
}