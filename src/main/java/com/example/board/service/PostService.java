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
    /*
     * 반환 타입은 **List<Post>**
     * List 는 자바에서 제공하는 자료구조 여러개의 post 객체 저장가능
     */
    public Post getPostById(Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id: " + id));
    }

    //게시글 생성
    public Post createPost(Post post){
        return postRepository.save(post);
    }
    //게시글 삭제
    public void deletePost(Long id){
        postRepository.deleteById(id);           
    }
    //void 반환: 이 메서드는 아무런 값을 반환하지 않는다
    /*
     * public void deletePost(Long id) {
    try     {
            postRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Invalid post Id: " + id);
        }
    }
        예외 처리하는 코드인데 삭제할 대상이 없을떄 
        발생하는 예외(EmptyResultDataAccessException)
        명확하게 에러 메시지 전달!!
     */
}