package uz.pdp.springadvanced;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import uz.pdp.springadvanced.entity.Post;
import uz.pdp.springadvanced.repository.PostRepository;

import java.net.URL;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
@EnableCaching
public class SpringadvancedApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }


    @Bean
    public ApplicationRunner applicationRunner(ObjectMapper objectMapper, PostRepository postRepository) {
        return (args -> {
            List<Post> posts = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/posts"), new TypeReference<>() {
            });
            postRepository.saveAll(posts);
        });
    }


    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        concurrentMapCacheManager.setCacheNames(List.of("posts", "users", "comments"));
        return concurrentMapCacheManager;
    }

}




