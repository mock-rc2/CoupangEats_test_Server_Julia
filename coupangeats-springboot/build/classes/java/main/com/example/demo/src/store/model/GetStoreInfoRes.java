package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreInfoRes {
    private int storeIdx;
    private String storeImage1Url;
    private String storeImage2Url;
    private String storeImage3Url;
    private String storeName;
    private double reviewStar;
    private String deliveryTime;
    private String deliveryInfo;
    private String minimumDelivery;
    private String storeAddress;
    private String storePhoneNum;
    private String storeBossName;
    private String businessLicense;
    private String companyName;
    private String storeOpenTime;
    private String storeIntro;
    private String foodInfo;
    private String couponInfo;
}