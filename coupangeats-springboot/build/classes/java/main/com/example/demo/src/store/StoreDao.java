package com.example.demo.src.store;

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
                        rs.getString("couponInfo")),
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
                        rs.getString("couponInfo")),
                getStoreInfoParams);
    }

    public List<GetStoreMenuRes> getStoreMenu(int menuCategoryIdx) {
        String getStoreMenuQuery = "select * from Store left join MenuCategory on Store.storeIdx=MenuCategory.storeIdx join Menu on Store.storeIdx=Menu.storeIdx where Menu.menuCategoryIdx = ?";
        int getStoreMenuParams = menuCategoryIdx;
        return this.jdbcTemplate.query(getStoreMenuQuery,
                (rs, rowNum) -> new GetStoreMenuRes(
                        rs.getInt("storeIdx"),
                        rs.getInt("menuCategoryIdx"),
                        rs.getString("menuCategoryName"),
                        rs.getString("menuName"),
                        rs.getString("menuPrice"),
                        rs.getString("menuIntro"),
                        rs.getString("menuImage1Url"),
                        rs.getString("orderStatus"),
                        rs.getString("reviewStatus")),
                getStoreMenuParams);
    }

  /**  public int createOrder(PostOrderReq postOrderReq){
        String createOrderQuery = "insert into Order (status) VALUES (?)";
        Object[] createOrderParams = new Object[]{postOrderReq.getStatus()};
        this.jdbcTemplate.update(createOrderQuery, createOrderParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    } */
}
