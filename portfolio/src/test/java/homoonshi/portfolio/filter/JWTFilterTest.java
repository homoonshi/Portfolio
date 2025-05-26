package homoonshi.portfolio.filter;

import homoonshi.portfolio.entity.CustomUserDetails;
import homoonshi.portfolio.entity.UserEntity;
import homoonshi.portfolio.repository.UserRepository;
import homoonshi.portfolio.util.JWTUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class JWTFilterTest {

    @Value("${JWT_LOGIN_URL}")
    String url;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTUtil jwtUtil;

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void before(){
        UserEntity userEntity = new UserEntity("user1", "password");
        userRepository.save(userEntity);
        userRepository.findByUsername(userEntity.getUsername());
    }

    @Test
    void testDoFilterAuthenticationJwt(){
        // given
        HttpServletResponse response = new MockHttpServletResponse();
        UserEntity user = userRepository.findByUsername("user1");

        // when
        String token = jwtUtil.createJwt(user.getUsername(), 60 * 60 * 10L);
        response.addHeader("Authorization", "Bearer "+token);

        String authorization = response.getHeader("Authorization");
        String jwt = authorization.split(" ")[1];

        String username = "";

        if(!jwtUtil.isExpired(jwt)){
            username = jwtUtil.getUsername(jwt);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword("temp");

        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        Authentication authToken =
                new UsernamePasswordAuthenticationToken(customUserDetails, null);

        SecurityContextHolder.getContext().setAuthentication(authToken);
        CustomUserDetails data = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // then
        assertEquals(user.getUsername(), username);
        assertEquals(user.getUsername(), data.getUsername());

    }

}