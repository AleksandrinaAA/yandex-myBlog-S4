package yandex.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PostController {

    @GetMapping("/posts/add")
    private String addPost() {
        return "add-post";
    }

    @GetMapping("/posts")
    private String getPosts() {
        return "posts";
    }
}
