package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewReq {
    private int reviewStar;
    private String reviewContents;
    private String reviewImageUrl;
}
