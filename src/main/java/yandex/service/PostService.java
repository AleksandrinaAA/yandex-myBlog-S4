package yandex.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yandex.model.dto.Paging;
import yandex.model.dto.Post;

import java.io.IOException;
import java.util.List;

@Service
public interface PostService {
    List<Post> getPosts(int pageNumber, int pageSize, String search);

    Post getPost(Long id);

    Paging getPaging(int pageNumber, int pageSize, String search);

    Post savePost(Post post, MultipartFile image);

    void deletePost(Long id);

    byte[] loadImage(Long id) throws IOException;
}
