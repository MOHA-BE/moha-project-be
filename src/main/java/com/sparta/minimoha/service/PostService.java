package com.sparta.minimoha.service;

import com.sparta.minimoha.dto.PostRequestDto;
import com.sparta.minimoha.dto.PostResponseDto;
import com.sparta.minimoha.model.Post;
import com.sparta.minimoha.repository.PostRepository;
import com.sparta.minimoha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AwsS3Service awsS3Service;

    // 게시글 조회하기
    @Transactional
    public List<PostResponseDto> getPost(){
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> responseDtoList = new ArrayList<>();

        for (Post post: posts){
            PostResponseDto postResponseDto = new PostResponseDto(post);
            responseDtoList.add(postResponseDto);
        }

        return responseDtoList;
    }

    //이미지 포함 게시글 수정하기
    @Transactional
    public ResponseEntity<String> updatePost(Long id, MultipartFile multipartFile, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("찾으시는 게시글이 존재하지 않습니다."));
        awsS3Service.deleteFile(post.getImageFilename());
        Map<String, String> map = awsS3Service.uploadFile(multipartFile);
        post = new Post(requestDto);
        post.setImageUrl(map.get("url"));
        post.setImageFilename(map.get("fileName"));
        postRepository.save(post);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    // 게시글 삭제하기
    @Transactional // User user 넣어주어야함
    public Long deletePost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제할 게시글이 없습니다.")
        );
        awsS3Service.deleteFile(post.getImageFilename());
        postRepository.deleteById(id);
        return id;
    }

    @Transactional
    public ResponseEntity<String> save(MultipartFile multipartFile, PostRequestDto requestDto) {
        Map<String, String> map = awsS3Service.uploadFile(multipartFile);

        Post post = new Post(requestDto, map.get("url"),map.get("fileName"));
        postRepository.save(post);
        return new ResponseEntity("게시글 저장이 정상적으로 완료되었습니다.", HttpStatus.OK);
    }
}



