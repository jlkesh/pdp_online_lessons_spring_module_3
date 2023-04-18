package uz.pdp.springadvanced.post;

import lombok.NonNull;

import java.util.List;

public interface PostRepository {

    Post get(String id);

    List<Post> getAll();
    List<Post> getAll(int page, int size);

    Post save(@NonNull Post post);

    List<Post> saveAll(@NonNull List<Post> posts);

    boolean delete(String id);

    boolean update(Post post);
}
