package uz.pdp.springadvanced.service;

import lombok.SneakyThrows;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.springadvanced.dto.PostCreateDto;
import uz.pdp.springadvanced.dto.PostUpdateDTO;
import uz.pdp.springadvanced.entity.Post;
import uz.pdp.springadvanced.repository.PostRepository;

import java.util.concurrent.TimeUnit;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CacheManager cacheManager;
    private final Cache cache;

    public PostServiceImpl(PostRepository postRepository, CacheManager cacheManager) {
        this.postRepository = postRepository;
        this.cacheManager = cacheManager;
        this.cache = cacheManager.getCache("posts");
    }


    @Override
    @Transactional
    public Post create(PostCreateDto dto) {
        return null;
    }

    @Override
    @SneakyThrows
    public Post get(Integer id) {
        Post cachedPost = cache.get(id, Post.class);
        if (cachedPost != null)
            return cachedPost;

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        TimeUnit.SECONDS.sleep(1);
        cache.put(id, post);
        return post;
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
        cache.evict(id);
    }

    @Override
    public void update(PostUpdateDTO dto) {
        Post post = get(dto.getId());
        post.setTitle(dto.getTitle());
        post.setBody(dto.getBody());
        postRepository.save(post);
        cache.put(dto.getId(), post);
    }
}
