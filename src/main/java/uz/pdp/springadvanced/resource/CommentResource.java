package uz.pdp.springadvanced.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uz.pdp.springadvanced.post.dto.CommentCreateDTO;
import uz.pdp.springadvanced.post.dto.CommentDTO;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentResource {
    private final WebClient webClient;

    @Value("${comments.url.postComments}")
    private String postCommentsURL;

    @Value("${comments.url.saveComments}")
    private String saveCommentsURL;


    public List<CommentDTO> getAllComments(@NonNull Integer postID) {
        return webClient
                .get()
                .uri(postCommentsURL, postID)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CommentDTO>>() {})
                .block();
    }

    public void saveAll(List<CommentCreateDTO> commentCreateDTOS) {
        webClient.post()
                .uri(saveCommentsURL)
                .body(BodyInserters.fromValue(commentCreateDTOS))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
