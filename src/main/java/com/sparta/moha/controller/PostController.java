package com.sparta.moha.controller;

import com.sparta.moha.dto.PostRequestDto;
import com.sparta.moha.dto.PostResponseDto;
import com.sparta.moha.model.Post;
import com.sparta.moha.model.User;
import com.sparta.moha.repository.PostRepository;
import com.sparta.moha.security.UserDetailsImpl;
import com.sparta.moha.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    // 게시글 작성하기
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();

        return postService.createPost(postRequestDto, user);
    }

    // 게시글 조회하기
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPost(){
        return postService.getPost();
    }

    // 게시글 수정하기
    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.updatePost(id, requestDto, userDetails);
        return id;
    }

    // 게시글 삭제하기
    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.deletePost(id, userDetails);
        return id;
    }

    // 게시글 상세 조회
    @GetMapping("/api/posts/{id}")
    public Post detailPost(@PathVariable Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("postId가 존재하지 않습니다.")
        );
        return post;
    }
}
