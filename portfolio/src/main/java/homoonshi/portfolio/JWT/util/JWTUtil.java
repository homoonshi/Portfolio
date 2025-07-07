package homoonshi.portfolio.JWT.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final RedisTemplate<String, String> redisTemplate;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret, RedisTemplate<String, String> redisTemplate){
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.redisTemplate = redisTemplate;
    }

    // 검증
    public String getUsername(String token){
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload()
                .get("username", String.class);
    }

    public Boolean isExpired(String token){
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload()
                .getExpiration().before(new Date());
    }

    // 생성
    public String createJwt(String username, Long expiredMs){
        return Jwts.builder()
                .claim("username", username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() +expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public void createRefreshToken(String UUID, String username, Long expiredMs) {
        redisTemplate.opsForValue().set(UUID, username, 7, TimeUnit.DAYS);
    }
}
