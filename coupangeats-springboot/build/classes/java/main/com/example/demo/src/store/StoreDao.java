package com.example.demo.src.store;

import com.example.demo.src.main.model.GetMainAdvertiseRes;
import com.example.demo.src.main.model.GetMainStoreRes;
import com.example.demo.src.store.model.*;
import com.example.demo.src.user.model.PostLoginReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StoreDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetStoreCategoryRes> getStoreCategory() {
        String getStoreCategoryQuery = "select * from Category";
        return this.jdbcTemplate.query(getStoreCategoryQuery,
                (rs, rowNum) -> new GetStoreCategoryRes(
                        rs.getInt("categoryIdx"),
                        rs.getString("categoryName"),
                        rs.getString("categoryIconUrl"))
        );
    }

    public List<GetStoreRes> getStore(int categoryIdx) {
        String getStoreQuery = "select * from Store left join Coupon on Store.storeIdx=Coupon.storeIdx join Review on Store.storeIdx=Review.storeIdx where Store.categoryIdx = ?";
        int getStoreInfoParams = categoryIdx;
        return this.jdbcTemplate.query(getStoreQuery,
                (rs, rowNum) -> new GetStoreRes(
                        rs.getInt("storeIdx"),
                        rs.getString("storeImage1Url"),
                        rs.getString("storeImage2Url"),
                        rs.getString("storeImage3Url"),
                        rs.getString("storeName"),
                        rs.getDouble("reviewStar"),
                        rs.getString("deliveryInfo"),
                        rs.getString("deliveryTime"),
                        rs.getString("couponInfo1")),
                getStoreInfoParams);
    }

    public GetStoreInfoRes getStoreInfo(int storeIdx) {
        String getStoreInfoQuery = "select * from Store left join Coupon on Store.storeIdx=Coupon.storeIdx join Review on Store.storeIdx=Review.storeIdx where Store.storeIdx = ?";
        int getStoreInfoParams = storeIdx;
        return this.jdbcTemplate.queryForObject(getStoreInfoQuery,
                (rs, rowNum) -> new GetStoreInfoRes(
                        rs.getInt("storeIdx"),
                        rs.getString("storeImage1Url"),
                        rs.getString("storeImage2Url"),
                        rs.getString("storeImage3Url"),
                        rs.getString("storeName"),
                        rs.getDouble("reviewStar"),
                        rs.getString("deliveryTime"),
                        rs.getString("deliveryInfo"),
                        rs.getString("minimumDelivery"),
                        rs.getString("storeAddress"),
                        rs.getString("storePhoneNum"),
                        rs.getString("storeBossName"),
                        rs.getString("businessLicense"),
                        rs.getString("companyName"),
                        rs.getString("storeOpenTime"),
                        rs.getString("storeIntro"),
                        rs.getString("foodInfo"),
                        rs.getString("couponInfo1")),
                getStoreInfoParams);
    }

    public GetStoreMenuCategoryRes getStoreMenuCategory(int menuCategoryIdx) {
        String getStoreMenuCategoryQuery = "select * from MenuCategory where menuCategoryIdx = ?";
        int getStoreMenuCategoryParams = menuCategoryIdx;
        return this.jdbcTemplate.queryForObject(getStoreMenuCategoryQuery,
                (rs, rowNum) -> new GetStoreMenuCategoryRes(
                        rs.getInt("menuCategoryIdx"),
                        rs.getString("menuCategoryName"),
                        rs.getString("menuCategoryIntro")),
                getStoreMenuCategoryParams);
    }

    public List<GetStoreMenuRes> getStoreMenu(int menuCategoryIdx) {
        String getStoreMenuQuery = "select * from Menu where menuCategoryIdx = ?";
        int getStoreMenuParams = menuCategoryIdx;
        return this.jdbcTemplate.query(getStoreMenuQuery,
                (rs, rowNum) -> new GetStoreMenuRes(
                        rs.getInt("menuCategoryIdx"),
                        rs.getInt("menuIdx"),
                        rs.getString("menuName"),
                        rs.getString("menuPrice"),
                        rs.getString("menuIntro"),
                        rs.getString("menuImage1Url"),
                        rs.getString("orderStatus"),
                        rs.getString("reviewStatus")),
                getStoreMenuParams);
    }

    public int createOrder(PostOrderReq postOrderReq){
        String createOrderQuery = "insert into OrderMenu (storeRequest, riderRequest, spoonFlag, status, cartIdx) VALUES (?,?,?,?,?)";
        Object[] createOrderParams = new Object[]{postOrderReq.getStoreRequest(), postOrderReq.getRiderRequest(), postOrderReq.getSpoonFlag(), postOrderReq.getStatus(), postOrderReq.getCartIdx()};
        this.jdbcTemplate.update(createOrderQuery, createOrderParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public GetOrderRes getOrder(int orderIdx) {
        String getOrderQuery = "select * from OrderMenu where orderIdx = ?";
        int getOrderParams = orderIdx;
        return this.jdbcTemplate.queryForObject(getOrderQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getInt("cartIdx")),
                getOrderParams);
    }

    public List<GetCouponRes> getCoupon(){
        String getCouponQuery = "select * from Coupon";
        return this.jdbcTemplate.query(getCouponQuery,
                (rs,rowNum) -> new GetCouponRes(
                        rs.getInt("couponIdx"),
                        rs.getString("couponInfoLong"),
                        rs.getString("couponInfo2"),
                        rs.getString("validationTime"))
        );
    }
}
