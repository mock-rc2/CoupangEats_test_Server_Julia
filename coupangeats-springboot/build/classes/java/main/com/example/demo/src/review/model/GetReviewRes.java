package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewRes {
    private int reviewIdx;
    private double reviewStar;
    private String reviewContents;
    private String reviewImageUrl;
}