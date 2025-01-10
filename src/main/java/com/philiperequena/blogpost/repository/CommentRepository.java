package com.philiperequena.blogpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.philiperequena.blogpost.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlogPostIdOrderByCreatedAtAsc(Long blogPostId);

}
