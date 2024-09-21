package com.example.board.service;

import java.util.List;

import com.example.board.domain.Post;
import com.example.board.repository.PostRepository;

public class PostService {
    private final PostRepository postRepository;
    
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    //게시글 불러오기
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    //특정게시글 불러오기
    public Post getPostById(Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id: " + id));
    }

}