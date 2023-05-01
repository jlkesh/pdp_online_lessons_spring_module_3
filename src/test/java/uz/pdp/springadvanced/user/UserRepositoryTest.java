package uz.pdp.springadvanced.user;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/*@TestPropertySource(locations = "/app.properties")*/
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveMethod() {
        User user = User.builder()
                .email("jlkesh@gmail.com")
                .username("jlkesh")
                .password("jlkesh123")
                .otp("123")
                .build();
        User actual = userRepository.save(user);
        assertNotNull(actual.getId());
    }

    @Test
    @Sql("/insert_data.sql")
    void testCountMethod() {
        long count = userRepository.count();
        System.out.println(count);
        assertEquals(10, count);
    }


}