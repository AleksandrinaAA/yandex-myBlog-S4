package yandex.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private String title;
    private String tags;
    private String text;
    private MultipartFile image;
}
