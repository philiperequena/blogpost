package com.philiperequena.blogpost.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.philiperequena.blogpost.dto.GetAllPostResponseDTO;
import com.philiperequena.blogpost.repository.BlogPostRepository;
import com.philiperequena.blogpost.repository.CommentRepository;

public class BlogPostServiceTest {

    @Mock
    private BlogPostRepository blogPostRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private BlogPostService blogPostService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBlogPostsWithCommentCount() {
        when(blogPostRepository.findAllWithCommentCount()).thenReturn(Arrays.asList(
            new GetAllPostResponseDTO(1L, "My First Post", 3L),
            new GetAllPostResponseDTO(2L, "My Second Post", 5L)
        ));

        List<GetAllPostResponseDTO> blogPostsWithCommentCount = blogPostService.getAllBlogPosts();

        assertEquals(2, blogPostsWithCommentCount.size()); // There should be two blog posts
        assertEquals("My First Post", blogPostsWithCommentCount.get(0).getTitle());
        assertEquals(3, blogPostsWithCommentCount.get(0).getCommentCount());
        assertEquals("My Second Post", blogPostsWithCommentCount.get(1).getTitle());
        assertEquals(5, blogPostsWithCommentCount.get(1).getCommentCount());
    }
}
