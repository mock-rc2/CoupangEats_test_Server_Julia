package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCouponRes {
    private int couponIdx;
    private String couponInfoLong;
    private String couponInfo2;
    private String validationTime;
}