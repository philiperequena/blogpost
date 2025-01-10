package com.philiperequena.blogpost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philiperequena.blogpost.dto.GetAllPostResponseDTO;
import com.philiperequena.blogpost.exception.NotFoundException;
import com.philiperequena.blogpost.model.BlogPost;
import com.philiperequena.blogpost.model.Comment;
import com.philiperequena.blogpost.repository.BlogPostRepository;
import com.philiperequena.blogpost.repository.CommentRepository;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private CommentRepository commentRepository;

    public BlogPost createBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public List<GetAllPostResponseDTO> getAllBlogPosts() {
        return blogPostRepository.findAllWithCommentCount();
    }

    public BlogPost getBlogPostById(Long postId) {
        return blogPostRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("BlogPost not found"));
    }

    public Comment addCommentToPost(Long postId, String commentContent) {
        BlogPost blogPost = getBlogPostById(postId);

        Comment comment = new Comment();
        comment.setContent(commentContent);
        comment.setBlogPost(blogPost);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsForPost(Long postId) {
        return commentRepository.findByBlogPostIdOrderByCreatedAtAsc(postId);
    }
}
