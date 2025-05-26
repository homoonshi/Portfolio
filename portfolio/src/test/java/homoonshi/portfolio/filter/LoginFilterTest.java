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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class LoginFilterTest {

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
    public void testSuccessfulAuthenticationJwt() throws Exception {
        // given
        HttpServletResponse response = new MockHttpServletResponse();
        UserEntity user = userRepository.findByUsername("user1");
        LoginFilter filter = new LoginFilter(null, jwtUtil, url);

        // when
        String token = jwtUtil.createJwt(user.getUsername(), 60 * 60 * 10L);
        response.addHeader("Authorization", "Bearer mocked-jwt-token");

        // then
        assertEquals("Bearer mocked-jwt-token", response.getHeader("Authorization"));

    }

}