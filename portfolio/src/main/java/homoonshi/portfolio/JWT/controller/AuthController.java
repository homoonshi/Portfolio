package homoonshi.portfolio.JWT.controller;

import homoonshi.portfolio.JWT.util.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final RedisTemplate<String, String> redisTemplate;
    private final JWTUtil jwtUtil;

    @PostMapping("/api/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        String refreshToken = null;

        if(cookies != null){
            for (Cookie cookie : cookies) {
                if("refreshToken".equals(cookie.getName())){
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        if(refreshToken == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No RefreshToken");
        }

        String username = redisTemplate.opsForValue().get(refreshToken);

        if(username == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token");
        }

        String newAccessToken = jwtUtil.createJwt(username, 60 * 10L);

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + newAccessToken)
                .build();
    }

}
