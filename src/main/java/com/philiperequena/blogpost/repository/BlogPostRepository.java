package com.philiperequena.blogpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.philiperequena.blogpost.dto.GetAllPostResponseDTO;
import com.philiperequena.blogpost.model.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    @Query("SELECT new com.philiperequena.blogpost.dto.GetAllPostResponseDTO(bp.id, bp.title, COUNT(c)) " +
            "FROM BlogPost bp LEFT JOIN Comment c ON bp.id = c.blogPost.id " +
            "GROUP BY bp.id ORDER BY bp.createdAt ASC")
    List<GetAllPostResponseDTO> findAllWithCommentCount();
}
