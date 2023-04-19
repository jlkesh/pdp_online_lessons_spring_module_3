package uz.pdp.springadvanced.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springadvanced.post.Post;

import java.io.Serializable;

/**
 * A DTO for the {@link Post} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDTO implements Serializable {
    private String title;
    private String body;
}