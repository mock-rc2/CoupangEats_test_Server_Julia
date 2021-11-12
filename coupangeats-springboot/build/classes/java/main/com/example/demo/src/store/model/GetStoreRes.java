package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreRes {
    private int storeIdx;
    private String storeImage1Url;
    private String storeImage2Url;
    private String storeImage3Url;
    private String storeName;
    private double reviewStar;
    private String deliveryInfo;
    private String deliveryTime;
    private String couponInfo;
}