package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostOrderReq {
    private int cartIdx;
    private String storeRequest;
    private String riderRequest;
    private String spoonFlag;
    private String status;
}