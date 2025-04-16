package yandex.service;

import lombok.NonNull;
import yandex.model.entities.Post;


public interface PostService {
    Post create(@NonNull Post post);
}
