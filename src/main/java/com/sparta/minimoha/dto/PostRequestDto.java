package com.sparta.minimoha.dto;

import lombok.*;


@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PostRequestDto {
    private String title;
    private String contents;
    private String category;
    private String name;
    private String imageUrl;
    private String imageFilename;

}
