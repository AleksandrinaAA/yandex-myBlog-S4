package yandex.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yandex.model.dto.Post;
import yandex.service.PostService;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public String getPosts(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            Model model) {

        model.addAttribute("posts", postService.getPosts(pageNumber, pageSize, search));
        model.addAttribute("search", search);
        model.addAttribute("paging", postService.getPaging(pageNumber, pageSize, search));
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "post";
    }

    @GetMapping("/posts/add")
    public String addPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post-add";
    }

    @PostMapping("/posts")
    public String addPost(
            @RequestParam String title,
            @RequestParam String text,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam(defaultValue = "") String tags) {

        Post post = new Post();
        post.setTitle(title);
        post.setText(text);
        // Обработка тегов
        Post savedPost = postService.savePost(post, image);
        return "redirect:/posts/" + savedPost.getId();
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "post-add";
    }

    @PostMapping("/posts/{id}")
    public String updatePost(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String text,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam(defaultValue = "") String tags) {

        Post post = postService.getPost(id);
        post.setTitle(title);
        post.setText(text);
        postService.savePost(post, image);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

    @GetMapping("/images/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) throws IOException {
        return postService.loadImage(id);
    }
}
