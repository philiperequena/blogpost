package com.philiperequena.blogpost.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequestDTO {

    @NotEmpty(message = "Comment content cannot be empty")
    private String content;

}
