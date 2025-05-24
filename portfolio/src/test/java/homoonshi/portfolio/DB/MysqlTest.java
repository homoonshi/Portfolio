package homoonshi.portfolio.DB;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
public class MysqlTest {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    @Test
    public void testConnection() {

        try(Connection con =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    )){
        }catch (Exception e){
            fail(e.getMessage());
        }

    }

}
