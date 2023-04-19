package uz.pdp.springadvanced.post;

import org.springframework.lang.NonNull;

public interface PostService {
    Post getPost(@NonNull Integer id) throws Exception;
}
