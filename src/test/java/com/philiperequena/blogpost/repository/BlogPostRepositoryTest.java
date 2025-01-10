package com.philiperequena.blogpost.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.philiperequena.blogpost.dto.GetAllPostResponseDTO;
import com.philiperequena.blogpost.model.BlogPost;


@DataJpaTest
public class BlogPostRepositoryTest {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Test
    public void testFindAllWithCommentCount() {
        BlogPost blogPost1 = new BlogPost();
        blogPost1.setTitle("First Post");
        blogPost1.setContent("Content of the first post");
        blogPostRepository.save(blogPost1);

        BlogPost blogPost2 = new BlogPost();
        blogPost2.setTitle("Second Post");
        blogPost2.setContent("Content of the second post");
        blogPostRepository.save(blogPost2);

        List<GetAllPostResponseDTO> result = blogPostRepository.findAllWithCommentCount();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
    }
}