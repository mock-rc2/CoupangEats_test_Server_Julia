package com.example.demo.src.review;


import com.example.demo.src.main.model.GetMainStoreRes;
import com.example.demo.src.review.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReviewDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createReview(PostReviewReq postReviewReq){
        String createReviewQuery = "insert into Review (reviewStar, reviewContents, reviewImageUrl) VALUES (?,?,?)";
        Object[] createReviewParams = new Object[]{postReviewReq.getReviewStar(), postReviewReq.getReviewContents(), postReviewReq.getReviewImageUrl()};
        this.jdbcTemplate.update(createReviewQuery, createReviewParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public List<GetReviewRes> getReview(){
        String getReviewQuery = "select * from Review";
        return this.jdbcTemplate.query(getReviewQuery,
                (rs,rowNum) -> new GetReviewRes(
                        rs.getInt("reviewIdx"),
                        rs.getInt("reviewStar"),
                        rs.getString("reviewContents"),
                        rs.getString("reviewImageUrl"))
        );
    }

    public int modifyReview(PatchReviewReq patchReviewReq){
        String modifyReviewQuery = "update Review set reviewContents = ? where reviewIdx = ? ";
        Object[] modifyReviewParams = new Object[]{patchReviewReq.getReviewContents(), patchReviewReq.getReviewIdx()};

        return this.jdbcTemplate.update(modifyReviewQuery,modifyReviewParams);
    }

    public int modifyReviewStatus(PatchReviewStatusReq patchReviewStatusReq){
        String modifyReviewStatusQuery = "update Review set isDeleted = ? where reviewIdx = ? ";
        Object[] modifyReviewStatusParams = new Object[]{patchReviewStatusReq.getIsDeleted(), patchReviewStatusReq.getReviewIdx()};

        return this.jdbcTemplate.update(modifyReviewStatusQuery,modifyReviewStatusParams);
    }
}
