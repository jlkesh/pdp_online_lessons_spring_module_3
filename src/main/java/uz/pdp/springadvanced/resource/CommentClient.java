package uz.pdp.springadvanced.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.springadvanced.post.dto.CommentCreateDTO;
import uz.pdp.springadvanced.post.dto.CommentDTO;

import java.util.List;


@FeignClient(value = "CommentClient", url = "${comments.url.base}")
public interface CommentClient {

    @GetMapping("/{id}/post")
    List<CommentDTO> saveAllComments(@PathVariable Integer id);

    @PostMapping("/saveAll")
    Void saveAllComments(@RequestBody List<CommentCreateDTO> commentCreateDTOS);

}
