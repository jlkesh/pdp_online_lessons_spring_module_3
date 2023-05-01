package uz.pdp.springadvanced.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerSimpleTest {

    UserController userController;
    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        userController = new UserController(userService);
    }

    @Test
    void create() {
        User user = User.builder().build();
        when(userService.create(user)).thenReturn(User.builder().id(1).build());
        User actual = userController.create(user);
        assertEquals(1, actual.getId());
        verify(userService, times(1)).create(user);
    }

}