package uz.pdp.springadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springadvanced.entity.Post;

import java.io.Serializable;

/**
 * A DTO for the {@link Post} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDto implements Serializable {
    private String title;
    private String body;
}