package yandex.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import yandex.AppConfig;
import yandex.infrastructure.PostController;
import yandex.model.dto.PostDto;

import java.io.UnsupportedEncodingException;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
class PostServiceTest {

    @Autowired
    private PostController postController;

    @Test
    void successfulSaveNewPost() throws UnsupportedEncodingException {
        PostDto postDto = new PostDto();
        postDto.setTitle("test post");
        postDto.setTags("tag");
        postDto.setText("I wrote post");
        postDto.setImage(new MockMultipartFile("image", "test.jpg", "image/jpeg", "test.jpg".getBytes("UTF-8")));
        postController.savePost(postDto);


    }
}
