package com.example.board.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.example.board.domain.Post;
import com.example.board.service.PostService;

public class PostController {

    // 의존성 주입: 객체를 직접 생성하지 않고 외부에서 주입받는 방식
    private final PostService postService;

    // final로 선언된 필드는 생성자를 통해 초기화해야 함
    // 생성자를 통해 PostService 주입

    public PostController(PostService postService) {
        this.postService = postService;
        /*
         * this.postService는 PostController 클래스 내부에
         * 선언된 필드인 postService를 의미
         */

        /*
         * this.postService = postService;: 생성자 매개변수로
         * 전달된 postService 값을 클래스의 postService 필드에 할당
         * 이렇게 하면 외부에서 주입된
         * PostService 객체를 PostController 내에서 사용 가능
         */
    }

    @GetMapping
    public String getAllPosts(Model model) {
        // addAttribute 메서드는 컨트롤러에서 데이터를 뷰로 전달할 때 사용,
        // 여기서는 posts라는 이름으로 게시글 리스트 데이터를 뷰 템플릿으로 전달
        model.addAttribute("posts", postService.getAllPosts());
        return "posts/list";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "posts/detail";
    }

    @GetMapping("/new")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/new";
    }

    //@PostMapping("/posts"):새 게시글 저장하고 목록 페이지로 리디렉션
    @PostMapping
    public String createPost(@ModelAttribute Post post) {
        postService.createPost(post);
        return "redirect:/posts";
    }

    //@DeleteMapping("/posts/{id}"): 게시글 삭제하고 목록페이지로 리디렉션
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
