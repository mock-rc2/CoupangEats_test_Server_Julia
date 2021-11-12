package com.example.demo.src.store;

import com.example.demo.src.user.model.PostLoginReq;
import com.example.demo.src.user.model.PostLoginRes;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.store.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/stores")
public class StoreController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final StoreProvider storeProvider;
    @Autowired
    private final StoreService storeService;
    @Autowired
    private final JwtService jwtService;


    public StoreController(StoreProvider storeProvider, StoreService storeService, JwtService jwtService) {
        this.storeProvider = storeProvider;
        this.storeService = storeService;
        this.jwtService = jwtService;
    }

    /**
     * 음식점 카테고리 조회 API
     * [GET] /stores/categories
     * @return BaseResponse<List<GetStoreCategoryRes>>
     */
    @ResponseBody
    @GetMapping("/categories") // (GET) 127.0.0.1:9000/app/stores/categories
    public BaseResponse<List<GetStoreCategoryRes>> getStoreCategory() {
        try {
            List<GetStoreCategoryRes> getStoreCategoryRes = storeProvider.getStoreCategory();
            return new BaseResponse<>(getStoreCategoryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 카테고리별 음식점 조회 API
     * [GET] /stores/category
     * @return BaseResponse<List<GetStoreRes>>
     */
    @ResponseBody
    @GetMapping("/category") // (GET) 127.0.0.1:9000/app/stores/category
    public BaseResponse<List<GetStoreRes>> getStore(@RequestParam(required = true) int categoryIdx) {
        try {
            List<GetStoreRes> getStoreRes = storeProvider.getStore(categoryIdx);
            return new BaseResponse<>(getStoreRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 음식점 정보 조회 API
     * [GET] /stores/:storeIdx
     * @return BaseResponse<List<GetStoreInfoRes>>
     */
    @ResponseBody
    @GetMapping("/{storeIdx}") // (GET) 127.0.0.1:9000/app/stores/:storeIdx
    public BaseResponse<GetStoreInfoRes> getStoreInfo(@PathVariable("storeIdx") int storeIdx) {
        try {
            GetStoreInfoRes getStoreInfoRes = storeProvider.getStoreInfo(storeIdx);
            return new BaseResponse<>(getStoreInfoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 음식점 메뉴 카테고리별 조회 API
     * [GET] /stores/menus
     * @return BaseResponse<List<GetStoreMenuRes>>
     */
    @ResponseBody
    @GetMapping("/menus") // (GET) 127.0.0.1:9000/app/stores/menus
    public BaseResponse<List<GetStoreMenuRes>> getStoreMenu(@RequestParam(required = true) int menuCategoryIdx) {
        try {
            List<GetStoreMenuRes> getStoreMenuRes = storeProvider.getStoreMenu(menuCategoryIdx);
            return new BaseResponse<>(getStoreMenuRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 주문 API
     * [POST] /stores/order
     * @return BaseResponse<PostOrderRes>
     */
   /** @ResponseBody
    @PostMapping("/order")
    public BaseResponse<PostOrderRes> createOrder(@RequestBody PostOrderReq postOrderReq) {
        try{
            PostOrderRes postOrderRes = storeService.createOrder(postOrderReq);
            return new BaseResponse<>(postOrderRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    } */
}

