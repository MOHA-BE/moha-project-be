package com.sparta.minimoha.repository;

import com.sparta.minimoha.model.Comment;
import com.sparta.minimoha.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostOrderByModifiedAtDesc(Post post);
    void deleteByPost(Post post);
}
