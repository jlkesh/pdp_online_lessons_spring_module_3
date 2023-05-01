package uz.pdp.springadvanced.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springadvanced.AppErrorDto;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
/*@RequiredArgsConstructor*/
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDto> handleValidationError(MethodArgumentNotValidException e, HttpServletRequest req) {
        Map<String, String> developerMessage = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors())
            developerMessage.put(fieldError.getField(), fieldError.getDefaultMessage());

        AppErrorDto errorDto = new AppErrorDto(
                req.getRequestURI(),
                400,
                "Invalid input",
                developerMessage
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
