package uz.pdp.springadvanced;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(User user) {
        String email = user.getEmail();

        Optional<User> userOptional;

        userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent())
            throw new RuntimeException("Email already taken: " + email);

        String username = user.getUsername();

        userOptional = userRepository.findByEmail(username);

        if (userOptional.isPresent())
            throw new RuntimeException("Username already taken: " + username);

        return userRepository.save(user);
    }

    public User get(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("USER  NOT FOUND => " + id));
    }

}
