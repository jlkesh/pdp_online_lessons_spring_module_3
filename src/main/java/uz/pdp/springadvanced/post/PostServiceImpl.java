package uz.pdp.springadvanced.post;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.resource.CommentResource;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final CommentResource commentResource;


    @Override
    public Post getPost(@NonNull Integer id) throws Exception {
        return commentResource.getPost(id);
    }
}
