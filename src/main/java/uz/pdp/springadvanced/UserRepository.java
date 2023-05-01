package uz.pdp.springadvanced;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class UserRepository {
    private static final Logger log = Logger.getLogger(UserRepository.class.getName());
    private static final List<User> users = new ArrayList<>();
    private final AtomicInteger generator = new AtomicInteger(3);

    static {
        users.add(new User(1, "john", "john@gmail.com", "john123"));
        users.add(new User(2, "nurislom", "nurislom@gmail.com", "nurislom123"));
    }

    public Optional<User> findById(Integer id) {
        log.info("REPOSITORY : GETTING USER BY ID => " + id);
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public User save(User user) {
        if (user == null)
            throw new RuntimeException("Persistent object can not be null");
        user.setId(generator.getAndIncrement());
        users.add(user);
        log.info("REPOSITORY : USER SAVED => " + user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        log.info("REPOSITORY : GETTING USER BY EMAIL: " + email);
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public Optional<User> findByUsername(String username) {
        log.info("REPOSITORY : GETTING USER BY USERNAME: " + username);
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
