package agus.gamejournal.authSecurity.controllers;

import agus.gamejournal.authSecurity.exception.TokenRefreshException;
import agus.gamejournal.authSecurity.models.ERole;
import agus.gamejournal.authSecurity.models.RefreshToken;
import agus.gamejournal.authSecurity.models.Role;
import agus.gamejournal.authSecurity.models.User;
import agus.gamejournal.authSecurity.payload.request.LoginRequest;
import agus.gamejournal.authSecurity.payload.request.SignUpRequest;
import agus.gamejournal.authSecurity.payload.response.JwtResponse;
import agus.gamejournal.authSecurity.payload.response.MessageResponse;
import agus.gamejournal.authSecurity.payload.response.TokenRefreshResponse;
import agus.gamejournal.authSecurity.repository.RoleRepository;
import agus.gamejournal.authSecurity.repository.UserRepository;
import agus.gamejournal.authSecurity.security.jwt.JwtUtils;
import agus.gamejournal.authSecurity.security.services.RefreshTokenService;
import agus.gamejournal.authSecurity.security.services.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        List<String> roles =
                userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        ResponseCookie jwtRefreshCookie =
                jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
        .body(
            new JwtResponse(
                    jwt,userDetails.getId(), userDetails.getUsername(),
                    userDetails.getEmail(), roles));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getJwtRefreshFromCookie(request);

        if((refreshToken!=null) && (refreshToken.length()>0)) {
            return refreshTokenService
                    .findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(
                            user -> {
                                String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                                return ResponseEntity.ok(new TokenRefreshResponse(token));
                            })
                    .orElseThrow(
                            () -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Refresh token is empty!"));

  }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username already exists"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email already exists"));
        }

        User user =
                new User(
                        signUpRequest.getUsername(),
                        signUpRequest.getEmail(),
                        passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole =
                    roleRepository
                            .findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(userRole);
        } else {
      strRoles.forEach(
          role -> {
            switch (role) {
              case "admin":
                Role adminRole =
                    roleRepository
                        .findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                roles.add(adminRole);
                break;
              case "mod":
                Role modRole =
                    roleRepository
                        .findByName(ERole.ROLE_MODERATOR)
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                roles.add(modRole);
                break;
              default:
                Role userRole =
                    roleRepository
                        .findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                roles.add(userRole);
            }
          });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered " + "successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal.toString()!="anonymousUser") {
            Long userId=((UserDetailsImpl)principal).getId();
            refreshTokenService.deleteByUserId(userId);
        }
        ResponseCookie jwtRefreshCookie=jwtUtils.getCleanJwtRefreshCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,jwtRefreshCookie.toString())
                .body(new MessageResponse("Successfully logged out!"));
    }
}
