package yandex.model.mappers;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yandex.model.dto.CreatePostDto;
import yandex.model.dto.ReturnPostDto;
import yandex.model.entities.Post;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.util.List;

import static java.lang.System.in;

@Service
@Slf4j
public class PostMapper {

    @Autowired
    private ServletContext servletContext;
    private static String uploadDir = "/images/";

    public Post toCreatedEntity(CreatePostDto createPostDto) {
        if (createPostDto == null) {
            return null;
        }
        Post post = new Post();
        post.setName(createPostDto.getTitle());
        post.setTag(createPostDto.getTags());
        post.setContent(createPostDto.getText());
        post.setImage(mapImage(createPostDto.getImage()));
        return post;
    }

    public ReturnPostDto toReturnedDTO(Post post) {
        if (post == null) {
            return null;
        }
        ReturnPostDto postDto = new ReturnPostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getName());
        postDto.setTags(post.getTag());
        postDto.setTextPreview(post.getContent());
        postDto.setLikesCount(0);
        postDto.setImage(post.getImage());
        return postDto;
    }

    public String mapImage(MultipartFile image) {
        if (image == null) {
            return null;
        }
        String fullPath, imageName;
        try {
            fullPath = servletContext.getRealPath(uploadDir);
            imageName = image.getOriginalFilename();
            File createdDir = new File(fullPath);
            createdDir.mkdirs();
            Files.copy(image.getInputStream(), Path.of(fullPath + imageName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Failed to map image dto to entity: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            log.error("Failed to map image dto to entity. Can not get path to transfer image: {}", e.getMessage());
            return null;
        }
        return imageName;
    }
}
