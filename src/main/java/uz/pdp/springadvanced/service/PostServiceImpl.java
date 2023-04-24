package uz.pdp.springadvanced.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.springadvanced.dto.PostCreateDto;
import uz.pdp.springadvanced.dto.PostUpdateDTO;
import uz.pdp.springadvanced.entity.Post;
import uz.pdp.springadvanced.repository.PostRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ConcurrentHashMap<Integer, Post> postsCache = new ConcurrentHashMap<>();

    @Override
    @Transactional
    public Post create(PostCreateDto dto) {
        return null;
    }

    @Override
    @SneakyThrows
    public Post get(Integer id) {
        Post cachedPost = postsCache.get(id);
        if (cachedPost != null) {
            return cachedPost;
        }
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        TimeUnit.SECONDS.sleep(1);
        postsCache.put(id, post);
        return post;
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
        postsCache.remove(id);
    }

    @Override
    public void update(PostUpdateDTO dto) {
        Post post = get(dto.getId());
        post.setTitle(dto.getTitle());
        post.setBody(dto.getBody());
        postRepository.save(post);
        postsCache.put(dto.getId(), post);
    }
}
