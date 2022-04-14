package com.sparta.minimoha.controller;

import com.sparta.minimoha.dto.CommentRequestDto;
//import com.sparta.minimoha.exception.HanghaeMiniException;
import com.sparta.minimoha.model.Comment;
import com.sparta.minimoha.security.UserDetailsImpl;
import com.sparta.minimoha.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {

        this.commentService = commentService;
    }

    // 댓글 등록
    @PostMapping("/comment")
    public Map<String, Object> createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Comment comment = commentService.createComment(commentRequestDto, userDetails);

        Map<String, Object> result = new HashMap<>();
        result.put("nickname", comment.getNickname());
        result.put("comment", comment.getComment());
        result.put("CreatedAt", comment.getCreatedAt());
        result.put("id", comment.getId());

        return result;
    }

    // 댓글 수정
    @PostMapping("/comment/{id}")
    public Map<String, Object> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");

        Comment comment = commentService.updateComment(id, commentRequestDto);
        result.put("comment", comment);
        return result;
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{id}")
    public Map<String, String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");

        return result;
    }


}
