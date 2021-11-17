package com.example.demo.src.main.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMainStoreRes {
    private int storeIdx;
    private String storeImage1Url;
    private String storeName;
    private double reviewStar;
    private String deliveryInfo;
    private String couponInfo1;
}