package uz.pdp.springadvanced.service;

import uz.pdp.springadvanced.dto.PostCreateDto;
import uz.pdp.springadvanced.dto.PostUpdateDTO;
import uz.pdp.springadvanced.entity.Post;

import java.util.List;

public interface PostService {
    Post create(PostCreateDto dto);

    Post get(Integer id);

    void delete(Integer id);

    Post update(PostUpdateDTO dto);

    List<Post> getAll();

}
