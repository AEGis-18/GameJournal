package agus.gamejournal.authSecurity.security.services;

import agus.gamejournal.authSecurity.exception.TokenRefreshException;
import agus.gamejournal.authSecurity.models.RefreshToken;
import agus.gamejournal.authSecurity.repository.RefreshTokenRepository;
import agus.gamejournal.authSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${agus.app.jwtRefreshExpirationMs}")
    private long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId ) {
        Optional<RefreshToken> existingToken=refreshTokenRepository.findByUserId(userId);

        if(existingToken.isPresent()) {
            if(existingToken.get().getExpiryDate().compareTo(Instant.now())>0){
                return existingToken.get();
            }
            refreshTokenRepository.delete(existingToken.get());
        }

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken=refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if(refreshToken.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(refreshToken);
            throw new TokenRefreshException(refreshToken.getToken(),"Refresh token " +
                    "has expired. Log in again");
        }

        return refreshToken;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
