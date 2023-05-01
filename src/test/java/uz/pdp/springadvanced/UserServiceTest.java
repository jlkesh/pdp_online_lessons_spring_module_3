package uz.pdp.springadvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        /*userRepository = mock(UserRepository.class);*/
        userService = new UserService(userRepository);
    }

    @Test
    void successfullyCreated() {
        User user = User.builder()
                .email("user@gmail.com")
                .username("user")
                .password("user123")
                .build();
        when(userRepository.save(user)).thenReturn(User.builder()
                .id(3)
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build());
        User actual = userService.create(user);
        assertEquals(3, actual.getId());
        assertEquals("user", actual.getUsername());
        assertEquals("user@gmail.com", actual.getEmail());
        assertEquals("user123", actual.getPassword());
        verify(userRepository, atLeast(1)).save(user);
    }

    @Test
    void canNotBeNull() {
        /*User user = User.builder()
                .email("john@gmail.com")
                .username("user")
                .password("user123")
                .build();
        when(userRepository.findByEmail("john@gmail.com"))
                .thenThrow(new RuntimeException("USER CAN NOT BE NULL"));*/
        assertThrows(RuntimeException.class, () -> userService.create(null))
                .printStackTrace();
    }

    @Test
    void emailAlreadyTakenTest() {
        User user = User.builder().email("john@gmail.com").build();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        assertThrows(RuntimeException.class, () -> userService.create(user))
                .printStackTrace();
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void userAlreadyTakenTest() {
        User user = User.builder().email("jlkesh@gmail.com").username("john").build();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(new User()));
        assertThrows(RuntimeException.class, () -> userService.create(user))
                .printStackTrace();
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void successfullySaveTest() {
        User user = User.builder().email("jlkesh@gmail.com").username("jlkesh").build();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(User.builder()
                .id(10)
                .email("jlkesh@gmail.com")
                .username("jlkesh").build());

        User actual = userService.create(user);

        assertEquals(10, actual.getId());
        assertEquals("jlkesh@gmail.com", actual.getEmail());
        assertEquals("jlkesh", actual.getUsername());

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void successfulGetTest() {
        User user = new User(1, "john", "john@gmail.com", "john123");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        User actual = userService.get(1);
        assertEquals(1, actual.getId());
        assertEquals(user.getEmail(), actual.getEmail());
        assertEquals(user.getUsername(), actual.getUsername());

        verify(userRepository, times(1)).findById(1);

    }

    @Test
    void failedGetTest() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.get(anyInt())).printStackTrace();
        verify(userRepository, times(1)).findById(anyInt());
    }

}