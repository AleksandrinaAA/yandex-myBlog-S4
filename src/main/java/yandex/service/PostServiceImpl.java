package yandex.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yandex.model.dto.Paging;
import yandex.model.dto.Post;
import yandex.repository.PostRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final Path rootLocation = Paths.get("upload-dir");

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public List<Post> getPosts(int pageNumber, int pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
        return postRepository.findAllByTitle(search, pageable);
    }

    @Override
    public Paging getPaging(int pageNumber, int pageSize, String search) {
        long totalItems = postRepository.count(search);
        return Paging.of(pageNumber, pageSize, totalItems);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Post not found"));
    }

    @Override
    public Post savePost(Post post, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            try {
                Files.copy(image.getInputStream(), rootLocation.resolve(filename));
                post.setImagePath(filename);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file", e);
            }
        }
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public byte[] loadImage(Long id) throws IOException {
        Post post = getPost(id);
        Path file = rootLocation.resolve(post.getImagePath());
        return Files.readAllBytes(file);
    }
}
