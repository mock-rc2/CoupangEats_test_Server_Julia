package com.example.demo.src.search;

import com.example.demo.src.store.model.PostOrderReq;
import com.example.demo.src.store.model.PostOrderRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.search.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/search")
public class SearchController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final SearchProvider searchProvider;
    @Autowired
    private final SearchService searchService;
    @Autowired
    private final JwtService jwtService;


    public SearchController(SearchProvider searchProvider, SearchService searchService, JwtService jwtService) {
        this.searchProvider = searchProvider;
        this.searchService = searchService;
        this.jwtService = jwtService;
    }

    /**
     * 검색어 입력 API
     * [POST] /search
     * @return BaseResponse<PostSearchRes>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostSearchRes> createSearch(@RequestBody PostSearchReq postSearchReq) {
        try{
            PostSearchRes postSearchRes = searchService.createSearch(postSearchReq);
            return new BaseResponse<>(postSearchRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    
}