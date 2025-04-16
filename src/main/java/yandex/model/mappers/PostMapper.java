package yandex.model.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import yandex.model.dto.PostDto;
import yandex.model.entities.Post;

import java.io.File;
import java.io.IOException;

@Slf4j
public class PostMapper {

    private static String uploadDir = "/uploads/";

    public static Post toEntity(PostDto postDto) {
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

    public static String mapImage(MultipartFile image) {
        if (image == null) {
            return null;
        }
        String fullPath, imageName;
        try {
            fullPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(uploadDir);
            imageName = image.getOriginalFilename();
            image.transferTo(new File(fullPath + imageName));
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
