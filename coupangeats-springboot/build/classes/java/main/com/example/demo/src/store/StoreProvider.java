package com.example.demo.src.store;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.store.model.*;
import com.example.demo.src.user.model.PostLoginReq;
import com.example.demo.src.user.model.PostLoginRes;
import com.example.demo.src.user.model.User;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class StoreProvider {

    private final StoreDao storeDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StoreProvider(StoreDao storeDao, JwtService jwtService) {
        this.storeDao = storeDao;
        this.jwtService = jwtService;
    }

    public List<GetStoreCategoryRes> getStoreCategory() throws BaseException {
        try {
            List<GetStoreCategoryRes> getStoreCategoryRes = storeDao.getStoreCategory();
            return getStoreCategoryRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetStoreRes> getStore(int categoryIdx) throws BaseException {
        try {
            List<GetStoreRes> getStoreRes = storeDao.getStore(categoryIdx);
            return getStoreRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetStoreInfoRes getStoreInfo(int storeIdx) throws BaseException {
        try {
            GetStoreInfoRes getStoreInfoRes = storeDao.getStoreInfo(storeIdx);
            return getStoreInfoRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetStoreMenuCategoryRes getStoreMenuCategory(int menuCategoryIdx) throws BaseException {
        try {
            GetStoreMenuCategoryRes getStoreMenuCategoryRes = storeDao.getStoreMenuCategory(menuCategoryIdx);
            return getStoreMenuCategoryRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetStoreMenuRes> getStoreMenu(int menuCategoryIdx) throws BaseException {
        try {
            List<GetStoreMenuRes> getStoreMenuRes = storeDao.getStoreMenu(menuCategoryIdx);
            return getStoreMenuRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetOrderRes getOrder(int orderIdx) throws BaseException {
        try {
            GetOrderRes getOrderRes = storeDao.getOrder(orderIdx);
            return getOrderRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCouponRes> getCoupon() throws BaseException {
        try {
            List<GetCouponRes> getCouponRes = storeDao.getCoupon();
            return getCouponRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
