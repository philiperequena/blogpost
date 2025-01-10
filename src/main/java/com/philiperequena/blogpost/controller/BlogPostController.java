package com.philiperequena.blogpost.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.philiperequena.blogpost.dto.CommentRequestDTO;
import com.philiperequena.blogpost.dto.GetAllPostResponseDTO;
import com.philiperequena.blogpost.model.BlogPost;
import com.philiperequena.blogpost.model.Comment;
import com.philiperequena.blogpost.service.BlogPostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public List<GetAllPostResponseDTO> getAllPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createPost(@RequestBody BlogPost blogPost) {
        return blogPostService.createBlogPost(blogPost);
    }

    @GetMapping("/{id}")
    public BlogPost getPostById(@PathVariable Long id) {
        return blogPostService.getBlogPostById(id);
    }

    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addComment(@PathVariable Long id, @RequestBody @Valid CommentRequestDTO commentRequestDTO) {
        return blogPostService.addCommentToPost(id, commentRequestDTO.getContent());
    }
    
    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id) {
        return blogPostService.getCommentsForPost(id);
    }
}
