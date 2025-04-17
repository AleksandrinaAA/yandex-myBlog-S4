package yandex.model.mappers;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import yandex.model.dto.PostDto;
import yandex.model.entities.Post;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class PostMapper {

    @Autowired
    private ServletContext servletContext;
    private static String uploadDir = "/uploads/";

    public Post toEntity(PostDto postDto) {
        if (postDto == null) {
            return null;
        }
        Post post = new Post();
        post.setName(postDto.getTitle());
        post.setTag(postDto.getTags());
        post.setContent(postDto.getText());
        post.setImage(mapImage(postDto.getImage()));
        return post;
    }

    public String mapImage(MultipartFile image) {
        if (image == null) {
            return null;
        }
        String fullPath, imageName;
        try {
            fullPath = servletContext.getRealPath(uploadDir);
            imageName = image.getOriginalFilename();
            image.transferTo(new File(fullPath));
        } catch (IOException e) {
            log.error("Failed to map image dto to entity: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            log.error("Failed to map image dto to entity. Can not get path to transfer image: {}", e.getMessage());
            return null;
        }
        return fullPath + imageName;
    }
}
