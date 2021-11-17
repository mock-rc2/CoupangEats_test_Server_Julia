package com.example.demo.src.main;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.main.model.*;
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
public class MainProvider {

    private final MainDao mainDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MainProvider(MainDao mainDao, JwtService jwtService) {
        this.mainDao = mainDao;
        this.jwtService = jwtService;
    }

    public List<GetMainStoreRes> getMainStore() throws BaseException{
        try{
            List<GetMainStoreRes> getMainStoreRes = mainDao.getMainStore();
            return getMainStoreRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetMainAdvertiseRes> getMainAdvertise() throws BaseException{
        try{
            List<GetMainAdvertiseRes> getMainAdvertiseRes = mainDao.getMainAdvertise();
            return getMainAdvertiseRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}