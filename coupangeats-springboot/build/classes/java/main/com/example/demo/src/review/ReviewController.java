package com.example.demo.src.review;

import com.example.demo.src.main.model.GetMainStoreRes;
import com.example.demo.src.review.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/stores/review")
public class ReviewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final JwtService jwtService;


    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService, JwtService jwtService) {
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
        this.jwtService = jwtService;
    }

    /**
     * 리뷰작성 API
     * [POST] /stores/review
     * @return BaseResponse<PostReviewRes>
     */
    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostReviewRes> createReview(@RequestBody PostReviewReq postReviewReq) {
        try{
            PostReviewRes postReviewRes = reviewService.createReview(postReviewReq);
            return new BaseResponse<>(postReviewRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 리뷰조회 API
     * [GET] /stores/review
     * @return BaseResponse<List<GetReviewRes>>
     */
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/stores/review
    public BaseResponse<List<GetReviewRes>> getReview() {
        try {
            List<GetReviewRes> getReviewRes = reviewProvider.getReview();
            return new BaseResponse<>(getReviewRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 리뷰수정 API
     * [PATCH] /stores/review/:reviewIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{reviewIdx}")
    public BaseResponse<String> modifyReview(@PathVariable("reviewIdx") int reviewIdx, @RequestBody Review review){
        try {
            PatchReviewReq patchReviewReq = new PatchReviewReq(reviewIdx,review.getReviewContents());
            reviewService.modifyReview(patchReviewReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 리뷰상태수정 API
     * [PATCH] /stores/review/:reviewIdx/status
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{reviewIdx}/status")
    public BaseResponse<String> modifyReviewStatus(@PathVariable("reviewIdx") int reviewIdx, @RequestBody ReviewStatus reviewstatus){
        try {
            PatchReviewStatusReq patchReviewStatusReq = new PatchReviewStatusReq(reviewIdx,reviewstatus.getIsDeleted());
            reviewService.modifyReviewStatus(patchReviewStatusReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}