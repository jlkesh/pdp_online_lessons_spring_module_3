package uz.pdp.springadvanced.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;

import java.net.URL;
import java.util.List;

public class PostRepositoryTest {
    public static void main(String[] args) throws Exception {
        PostRepository postRepository = new PostRepositoryImpl();
        Post post = Post.builder()
                .mongoID(new ObjectId("643e218ee425ad490b17f679"))
                .id(200)
                .userId(210)
                .title("Post Title")
                .body("Post Body")
                .build();
        /*Post savedPost = postRepository.save(post);
        System.out.println("savedPost = " + savedPost);
        */
        /*URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        ObjectMapper objectMapper = new ObjectMapper();

        List<Post> posts = objectMapper.readValue(url, new TypeReference<>() {});
        List<Post> savedPosts = postRepository.saveAll(posts);
        for (Post savedPost : savedPosts) {
            System.out.println("savedPost = " + savedPost);
        }*/

        postRepository.getAll(0, 100).forEach(System.out::println);


    }
}
