package yandex.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import yandex.model.entities.Post;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
