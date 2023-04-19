package uz.pdp.springadvanced.post;

import org.springframework.lang.NonNull;
import uz.pdp.springadvanced.post.dto.CommentCreateDTO;
import uz.pdp.springadvanced.post.dto.PostCreateDTO;
import uz.pdp.springadvanced.post.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO getPost(@NonNull Integer id);
    PostDTO createPost(@NonNull PostCreateDTO dto);

    void createComment(@NonNull List<CommentCreateDTO> dtos);
}
