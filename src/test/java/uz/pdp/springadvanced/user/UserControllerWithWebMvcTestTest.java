package uz.pdp.springadvanced.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.springadvanced.AppErrorDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Slf4j
@WebMvcTest
class UserControllerWithWebMvcTestTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void create() throws Exception {
        User user = User.builder()
                .email("jlkesh@gmail.com")
                .username("jlkesh")
                .password("jlkesh123")
                .otp("123")
                .build();
        when(userService.create(user)).thenReturn(User.builder().id(1).build());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        verify(userService, times(1)).create(user);
    }


    @Test
    void createSuccessfulResponse() throws Exception {
        User user = User.builder()
                .email("jlkesh@gmail.com")
                .username("jlkesh")
                .password("jlkesh123")
                .otp("123")
                .build();

        when(userService.create(user)).thenReturn(User.builder().id(1).build());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        /*ModelAndView modelAndView = mvcResult.getModelAndView();*/
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        User returnedUser = objectMapper.readValue(contentAsString, User.class);
        assertEquals(1, returnedUser.getId());
        verify(userService, times(1)).create(user);
    }

    @Test
    void createSuccessfulResponseWithJsonPath() throws Exception {
        User user = User.builder()
                .email("jlkesh@gmail.com")
                .username("jlkesh")
                .password("jlkesh123")
                .otp("123")
                .build();

        when(userService.create(user)).thenReturn(User.builder()
                .id(1)
                .email("jlkesh@gmail.com")
                .username("jlkesh")
                .password("jlkesh123")
                .otp("123")
                .build());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("jlkesh@gmail.com"))
                .andExpect(jsonPath("$.username").value("jlkesh"))
                .andExpect(jsonPath("$.password").value("jlkesh123"));

        verify(userService, times(1)).create(user);
    }

    @Test
    void createBadRequestResponse() throws Exception {
        User user = User.builder()
                .password("jlkesh123")
                .otp("123")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void createBadRequestResponseWithTranslatedExceptions() throws Exception {
        User user = User.builder()
                .password("jlkesh123")
                .otp("123")
                .build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        AppErrorDto errorDto = objectMapper.readValue(contentAsString, AppErrorDto.class);
        assertEquals("Invalid input", errorDto.getFriendlyMessage());
        assertEquals("/api/users", errorDto.getErrorPath());
        assertEquals(400, errorDto.getErrorCode());
    }

    @Test
    void createBadRequestResponseWithTranslatedExceptionsWithJsonPath() throws Exception {
        User user = User.builder()
                .password("jlkesh123")
                .otp("123")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.errorPath").value("/api/users"))
                .andExpect(jsonPath("$.friendlyMessage").value("Invalid input"))
                .andExpect(jsonPath("$.developerMessage").hasJsonPath())
                .andExpect(jsonPath("$.developerMessage.email").value("email can not be blank"))
                .andExpect(jsonPath("$.developerMessage.username").value("username can not be blank"));
    }


}