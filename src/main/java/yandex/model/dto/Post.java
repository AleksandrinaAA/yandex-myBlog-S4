package yandex.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String text;
//    private List<Comment> comments = new ArrayList<>();
    private long likesCount;
//    private List<String> tags = new ArrayList<>();
    private LocalDateTime createdAt;
    private String imagePath;

    public String getTextPreview() {
        if (text == null || text.isEmpty()) return "";
        String[] paragraphs = text.split("\n");
        return paragraphs.length > 0 ? paragraphs[0] : "";
    }

//    public String getTagsAsText() {
//        return String.join(", ", tags);
//    }

    public List<String> getTextParts() {
        if (text == null) return List.of();
        return List.of(text.split("\n"));
    }
}
