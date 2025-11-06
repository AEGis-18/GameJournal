package agus.gamejournal.authSecurity.security.jwt;

import agus.gamejournal.authSecurity.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${agus.app.jwtSecret}")
  private String jwtSecret;

  @Value("${agus.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  @Value("${agus.app.jwtRefreshCookiePath}")
  private String jwtRefreshCookiePath;

  @Value("${agus.app.jwtRefreshCookieName}")
  private String jwtRefreshCookie;

  @Value("${agus.app.jwtRefreshExpirationMs}")
  private int jwtRefreshExpirationMs;

  public ResponseCookie generateRefreshJwtCookie(String refreshToken) {
    return generateCookie(jwtRefreshCookie, refreshToken, jwtRefreshCookiePath);
  }

  public String generateToken(UserDetailsImpl userPrincipal) {
    return generateTokenFromUsername(userPrincipal.getUsername());
  }

  public String generateTokenFromUsername(String username) {

    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(new Date().getTime() + jwtExpirationMs))
        .signWith(key(), Jwts.SIG.HS256)
        .compact();
  }

  public String getJwtRefreshFromCookie(HttpServletRequest request) {
    return getCookieValueByName(request, jwtRefreshCookie);
  }

  private ResponseCookie generateCookie(String name, String value, String path) {
    ResponseCookie cookie = ResponseCookie
            .from(name, value)
            .path(path)
            .httpOnly(true)
            .maxAge(jwtRefreshExpirationMs)
            .secure(true)
            .sameSite("Strict")
            .build();

    return cookie;
  }

  public ResponseCookie getCleanJwtRefreshCookie() {
    ResponseCookie cookie =
        ResponseCookie.from(jwtRefreshCookie, null)
                .path(jwtRefreshCookiePath)
                .httpOnly(true)
                .maxAge(jwtRefreshExpirationMs)
                .secure(true)
                .sameSite("Strict")
                .build();

    return cookie;
  }

  public String getUserNameFromToken(String token) {
    return Jwts.parser()
        .verifyWith(key())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  private String getCookieValueByName(HttpServletRequest request, String name) {
    Cookie cookie = WebUtils.getCookie(request, name);
    if (cookie != null) {
      return cookie.getValue();
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().verifyWith(key()).build().parse(token);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

  private SecretKey key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }
}
