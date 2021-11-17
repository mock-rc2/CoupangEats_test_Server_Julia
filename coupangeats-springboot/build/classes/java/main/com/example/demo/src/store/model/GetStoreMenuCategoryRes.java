package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreMenuCategoryRes {
    private int menuCategoryIdx;
    private String menuCategoryName;
    private String menuCategoryIntro;
}