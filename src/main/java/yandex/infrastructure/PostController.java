package yandex.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yandex.model.dto.PostDto;
import yandex.model.entities.Post;
import yandex.model.mappers.PostMapper;
import yandex.service.PostService;

@Controller
@RequestMapping
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private PostMapper postMapper;

    @GetMapping("/")
    public String home() {
        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.readAllPosts());
        return "posts";
    }

    @GetMapping("/posts/add")
    public String addPost() {
        return "add-post";
    }

    @PostMapping("/posts")
    public Post savePost(@ModelAttribute PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        return postService.create(post);
    }

//    @PostMapping("/posts/{id}")
//    private String editPost(@PathVariable(required = false) String id) {
//        return "posts";
//    }


}
