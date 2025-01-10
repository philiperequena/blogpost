package com.philiperequena.blogpost.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAllPostResponseDTO {

    private Long id;
    private String title;
    private Long commentCount;

    public GetAllPostResponseDTO(Long id, String title, Long commentCount) {
        this.id = id;
        this.title = title;
        this.commentCount = commentCount;
    }

}
