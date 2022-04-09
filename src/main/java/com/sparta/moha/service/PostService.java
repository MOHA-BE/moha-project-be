package com.sparta.moha.service;

import com.sparta.moha.dto.PostRequestDto;
import com.sparta.moha.dto.PostResponseDto;
import com.sparta.moha.model.ImageUrl;
import com.sparta.moha.model.Post;
import com.sparta.moha.model.User;
import com.sparta.moha.repository.ImageUrlRepository;
import com.sparta.moha.repository.PostRepository;
import com.sparta.moha.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostService {
    private final ImageUrlRepository imageUrlRepository;
    private final PostRepository postRepository;

    // 게시글 작성하기
    @Transactional
    public Post createPost(PostRequestDto requestDto, User user){
        List<ImageUrl> imageUrlList = new ArrayList<>();
        for (ImageUrl imageUrl: requestDto.getImageUrlList()
             ) {
            imageUrlList.add(imageUrlRepository.save(imageUrl));
        }

        String title = requestDto.getTitle(); // 제목
        String desc = requestDto.getDesc(); // 내용
        String category = requestDto.getCategory(); // 지역 카테고리

        if (title == null || desc == null || category == null){
            throw new IllegalArgumentException("제목, 내용, 지역 카테고리 모두 빠짐없이 입력해주세요");
        }
        if (desc.length() > 1000){
            throw new IllegalArgumentException("내용은 1000자 이하로 입력해주세요.");
        }

        Post post = new Post(requestDto, user, imageUrlList);
        postRepository.save(post);
        return post;
    }


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

    // 게시글 수정하기
    @Transactional
    public Post updatePost(Long id, PostRequestDto requestDto, UserDetailsImpl userDetails){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정할 게시글이 없습니다?")
        );

        User user = post.getUser();
        if (userDetails.getUser() != user) throw new IllegalArgumentException("작성자가 아니면 수정할 수 없습니다.");
        if (requestDto.getDesc() == null) throw new IllegalArgumentException("내용을 입력해주세요.");
        if (requestDto.getDesc().length() > 1000) throw new IllegalArgumentException("내용은 1000자 이하로 입력해주세요.");

        post.updatePost(requestDto);
        postRepository.save(post);

        return post;
    }

    // 게시글 삭제하기
    @Transactional
    public Long deletePost(Long id, UserDetailsImpl userDetails){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제할 게시글이 없습니다.")
        );

        User user = post.getUser();
        Long postId = user.getId();

        if (!Objects.equals(userDetails.getUser().getId(), postId)){
            throw new IllegalArgumentException("작성자가 아니면 삭제할 수 없습니다.");
        }

        postRepository.deleteById(id);
        return id;
    }
}
