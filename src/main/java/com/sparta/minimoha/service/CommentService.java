package com.sparta.minimoha.service;

import com.sparta.minimoha.dto.CommentRequestDto;
import com.sparta.minimoha.model.Comment;
import com.sparta.minimoha.model.Post;
import com.sparta.minimoha.repository.CommentRepository;
import com.sparta.minimoha.repository.PostRepository;
import com.sparta.minimoha.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Comment> getComments(Post post) {

        return commentRepository.findAllByPostOrderByModifiedAtDesc(post);
    }

    //수정
    @Transactional
    public Comment updateComment(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElse(null);
        comment.update(commentRequestDto);

        return comment;
    }

    //삭제
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    //등록
    public Comment createComment(CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("에러")
        );

        String nickname = "test 확인";
        if (userDetails != null) {
            nickname = userDetails.getUser().getNickname();
        }
        Comment comment = new Comment(nickname, commentRequestDto.getComment(), post);
        Comment saveComment = commentRepository.save(comment);

        return saveComment;
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(null);
        commentRepository.deleteByPost(post);
    }
}