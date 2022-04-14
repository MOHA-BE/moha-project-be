package com.sparta.minimoha.controller;

import com.sparta.minimoha.dto.PostRequestDto;
import com.sparta.minimoha.dto.PostResponseDto;
import com.sparta.minimoha.model.Post;
import com.sparta.minimoha.repository.PostRepository;
import com.sparta.minimoha.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    // 게시글 조회하기
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPost(){
        return postService.getPost();
    }

    // 이미지 포함 게시글 수정하기
    @PutMapping("/api/posts/{id}")
    public ResponseEntity postUpdate(@PathVariable Long id ,
                                     @RequestPart("file") MultipartFile multipartFile ,
                                     @RequestPart("information") PostRequestDto requestDto){
        return postService.updatePost(id ,multipartFile ,requestDto);
    }

    // 게시글 삭제하기
    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postService.deletePost(id);
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

    // 이미지 업로드 , 글 작성
    @PostMapping("/api/posts")
    public ResponseEntity postSave(@RequestPart("file") MultipartFile multipartFile ,
                                   @RequestPart("information") PostRequestDto requestDto
                                   ){
        return postService.save(multipartFile , requestDto);
    }
}
