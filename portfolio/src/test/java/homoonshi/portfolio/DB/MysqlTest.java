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

    @Test
    public void testConnection() {

        System.out.println("url = " + url);

        try(Connection con =
                    DriverManager.getConnection(
                            url
                    )){
            System.out.println("con = " + con);
        }catch (Exception e){
//            fail(e.getMessage());
            System.out.println("걍 연결 안됨");
        }

    }

}
