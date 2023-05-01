package uz.pdp.springadvanced.user;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(@NonNull User user) {
        log.info("Saving User : {}", user);
        return userRepository.save(user);
    }

    public User get(@NonNull Integer id) {
        log.info("Getting User With ID : {}", id);
        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        log.info("Returning User : {}", user);
        return user;
    }


    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
