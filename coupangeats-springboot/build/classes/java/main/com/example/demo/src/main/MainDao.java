package com.example.demo.src.main;


import com.example.demo.src.main.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MainDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetMainStoreRes> getMainStore(){
        String getMainStoreQuery = "select * from Store left join Coupon on Store.storeIdx=Coupon.storeIdx join Review on Store.storeIdx=Review.storeIdx ";
        return this.jdbcTemplate.query(getMainStoreQuery,
                (rs,rowNum) -> new GetMainStoreRes(
                        rs.getInt("storeIdx"),
                        rs.getString("storeImage1Url"),
                        rs.getString("storeName"),
                        rs.getDouble("reviewStar"),
                        rs.getString("deliveryInfo"),
                        rs.getString("couponInfo1"))
        );
    }

    public List<GetMainAdvertiseRes> getMainAdvertise(){
        String getMainAdvertiseQuery = "select * from Advertisement";
        return this.jdbcTemplate.query(getMainAdvertiseQuery,
                (rs,rowNum) -> new GetMainAdvertiseRes(
                        rs.getInt("advertisementIdx"),
                        rs.getString("advertisementUrl"))
        );
    }
}