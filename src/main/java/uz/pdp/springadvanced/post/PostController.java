package uz.pdp.springadvanced.post;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springadvanced.post.dto.CommentCreateDTO;
import uz.pdp.springadvanced.post.dto.PostCreateDTO;
import uz.pdp.springadvanced.post.dto.PostDTO;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Integer id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateDTO dto) {
        return ResponseEntity.ok(postService.createPost(dto));
    }

    @PostMapping("/comment")
    public ResponseEntity<Void> createComments(@RequestBody List<CommentCreateDTO> dtos) {
        postService.createComment(dtos);
        return ResponseEntity.noContent().build();
    }

}
