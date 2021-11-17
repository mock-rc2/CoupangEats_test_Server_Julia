package com.example.demo.src.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.main.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/main")
public class MainController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final MainProvider mainProvider;
    @Autowired
    private final MainService mainService;
    @Autowired
    private final JwtService jwtService;


    public MainController(MainProvider mainProvider, MainService mainService, JwtService jwtService) {
        this.mainProvider = mainProvider;
        this.mainService = mainService;
        this.jwtService = jwtService;
    }

    /**
     * 메인화면 음식점 조회 API
     * [GET] /main/store
     * @return BaseResponse<List<GetMainStoreRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/store") // (GET) 127.0.0.1:9000/app/main/store
    public BaseResponse<List<GetMainStoreRes>> getMainStore() {
        try {
            List<GetMainStoreRes> getMainStoreRes = mainProvider.getMainStore();
            return new BaseResponse<>(getMainStoreRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 메인화면 광고 API
     * [GET] /main/advertise
     * @return BaseResponse<List<GetMainAdvertiseRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/advertise") // (GET) 127.0.0.1:9000/app/main/advertise
    public BaseResponse<List<GetMainAdvertiseRes>> getMainAdvertise() {
        try {
            List<GetMainAdvertiseRes> getMainAdvertiseRes = mainProvider.getMainAdvertise();
            return new BaseResponse<>(getMainAdvertiseRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}