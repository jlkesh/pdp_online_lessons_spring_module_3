package uz.pdp.springadvanced.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springadvanced.dto.PostCreateDto;
import uz.pdp.springadvanced.dto.PostUpdateDTO;
import uz.pdp.springadvanced.entity.Post;
import uz.pdp.springadvanced.service.PostService;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostCreateDto dto) {
        return ResponseEntity.status(201).body(postService.create(dto));
    }

    @GetMapping("/{id}")
    public Post get(@PathVariable Integer id) {
        return postService.get(id);
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        postService.delete(id);
    }

    @PutMapping
    public void update(@RequestBody PostUpdateDTO dto) {
        postService.update(dto);
    }

}
