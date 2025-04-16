package yandex.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yandex.model.entities.Post;
import yandex.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Transactional
    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }
}
