package uz.pdp.springadvanced;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.pdp.springadvanced.post.Post;
import uz.pdp.springadvanced.post.PostRepository;
import uz.pdp.springadvanced.post.Rating;
import uz.pdp.springadvanced.user.UserRepository;
import uz.pdp.springadvanced.user.Users;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringadvancedApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, PostRepository postRepository) {
        return args -> {
            Users josh = new Users(1, "Josh", "Long");
            Users john = new Users(2, "John", "Leg");
            userRepository.saveAllAndFlush(Arrays.asList(josh, john));
            var johnPosts = List.<Post>of(
                    new Post(1, "Learn GraphQL Today", "Learn GraphQL Today(Body)", Rating.FIVE_STARS, john),
                    new Post(2, "Springdoc in Spring Boot", "Springdoc in Spring Boot(Body)", Rating.FOUR_STARS, john),
                    new Post(3, "Reactive Thinking", "Reactive Thinking(Body)", Rating.FIVE_STARS, john),
                    new Post(4, "Reactive Spring", "Reactive Spring(Body)", Rating.FIVE_STARS, josh)
            );
            postRepository.saveAll(johnPosts);
        };
    }
}
