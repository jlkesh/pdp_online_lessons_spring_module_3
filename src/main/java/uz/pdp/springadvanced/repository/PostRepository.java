package uz.pdp.springadvanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springadvanced.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}