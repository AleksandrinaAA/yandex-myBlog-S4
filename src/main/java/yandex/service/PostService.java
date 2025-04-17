package yandex.service;

import lombok.NonNull;
import yandex.model.entities.Post;

import java.util.List;


public interface PostService {
    Post create(@NonNull Post post);

    List<Post> readAllPosts();
}
