package com.sparta.moha.dto;

import com.sparta.moha.model.ImageUrl;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImageUrlRequestDto {
    private List<ImageUrl> imageUrlList;
}
