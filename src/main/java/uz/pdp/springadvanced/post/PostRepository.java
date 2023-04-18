package uz.pdp.springadvanced.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    /*@Query("{ post_title : { $regex :  '^?0.*'}, userId : { $gt : ?1}   }")
    List<Post> findAllByTitleCustom(String title, int userId);*/

    List<Post> findAllByTitleRegexAndUserIdGreaterThan(String regex, int userId);
}
