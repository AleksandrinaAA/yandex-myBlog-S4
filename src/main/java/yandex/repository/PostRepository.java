package yandex.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yandex.model.dto.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitle(String search, Pageable pageable);

    @Query("SELECT count(*) from Post where title LIKE ?1")
    long count(String search);
}
