package uz.pdp.springadvanced.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springadvanced.post.Post;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Post} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO implements Serializable {
    private Integer id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
}