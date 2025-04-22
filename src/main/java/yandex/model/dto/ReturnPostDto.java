package yandex.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnPostDto {

    private UUID id;
    private String title;
    private String tags;
    private String textPreview;
    private List<String> comments = new ArrayList<>();
    private long likesCount;
    private String image;
}
