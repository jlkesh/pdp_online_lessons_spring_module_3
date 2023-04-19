package uz.pdp.springadvanced.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post implements Serializable {
    private Integer id;
    private String title;
    private String body;
    private Integer userId;
}