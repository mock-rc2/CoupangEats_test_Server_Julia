package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreMenuRes {
    private int menuCategoryIdx;
    private int menuIdx;
    private String menuName;
    private String menuPrice;
    private String menuIntro;
    private String menuImage1Url;
    private String orderStatus;
    private String reviewStatus;
}