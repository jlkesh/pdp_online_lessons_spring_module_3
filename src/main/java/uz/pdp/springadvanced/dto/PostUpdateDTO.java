package uz.pdp.springadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link uz.pdp.springadvanced.entity.Post} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDTO implements Serializable {
    private Integer id;
    private String title;
    private String body;
}