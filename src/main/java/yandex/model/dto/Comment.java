package yandex.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Comment {
    private Long id;
    private Long postId;
    private String text;
    private LocalDateTime createdAt;
}
