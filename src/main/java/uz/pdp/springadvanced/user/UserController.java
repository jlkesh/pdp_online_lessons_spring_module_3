package uz.pdp.springadvanced.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springadvanced.post.PostRepository;

import java.util.List;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
class UserController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @GetMapping
    public List<Users> getAll() {
        List<Users> authors = userRepository.findAll();
        return authors;
    }

    @PutMapping
    public Users update(@RequestBody UserUpdateDTO dto) {
        Users user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Not  found"));
        if (Objects.nonNull(dto.getFirstName()))
            user.setFirstName(dto.getFirstName());
        if (Objects.nonNull(dto.getLastName()))
            user.setLastName(dto.getLastName());
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }


}


