package yandex.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PostController {

    @GetMapping("/")
    private String home() {
        return "redirect:/posts";
    }

    @GetMapping("/posts")
    private String getPosts() {
        return "posts";
    }

    @GetMapping("/posts/add")
    private String addPost() {
        return "add-post";
    }

    @PostMapping("/posts")
    private String savePost() {
        return "posts";
    }

    @PostMapping("/posts/{id}")
    private String editPost(@PathVariable(required = false) String id) {
        return "posts";
    }


}
