package yandex.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yandex.model.dto.CreatePostDto;
import yandex.model.dto.ReturnPostDto;
import yandex.model.entities.Post;
import yandex.model.mappers.PostMapper;
import yandex.service.PostService;

import java.util.List;

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
        List<Post> posts = postService.readAllPosts();
        List<ReturnPostDto> returnPostDtos = posts.stream().map(post -> postMapper.toReturnedDTO(post)).toList();
        model.addAttribute("posts", returnPostDtos);
        return "posts";
    }

    @GetMapping("/posts/add")
    public String addPost() {
        return "add-post";
    }

    @PostMapping("/posts")
    public Post savePost(@ModelAttribute CreatePostDto createPostDto) {
        Post post = postMapper.toCreatedEntity(createPostDto);
        return postService.create(post);
    }

//    @PostMapping("/posts/{id}")
//    private String editPost(@PathVariable(required = false) String id) {
//        return "posts";
//    }


}
