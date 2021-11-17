package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userEmail"),
                        rs.getString("userName"),
                        rs.getString("userPhoneNum"),
                        rs.getString("userFlag"))
                );
    }

    public GetUserAddressRes getUserAddress(int userIdx){
        String getUserAddressQuery = "select * from User where userIdx = ?";
        int getUserAddressParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserAddressQuery,
                (rs, rowNum) -> new GetUserAddressRes(
                        rs.getInt("userIdx"),
                        rs.getString("userAddress")),
                getUserAddressParams);
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from User where userEmail =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUsersByEmailParams);
    }

    public GetUserInfoRes getUser(int userIdx){
        String getUserInfoQuery = "select * from User where userIdx = ?";
        int getUserInfoParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserInfoQuery,
                (rs, rowNum) -> new GetUserInfoRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("userPhoneNum")),
                getUserInfoParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into User (userEmail, userPassword, userName, userPhoneNum, userFlag) VALUES (?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserEmail(), postUserReq.getUserPassword(), postUserReq.getUserName(), postUserReq.getUserPhoneNum(), postUserReq.getUserFlag()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select userEmail from User where userEmail = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int setUserAddress(PatchUserReq patchUserReq){
        String setUserAddressQuery = "update User set userAddress = ? where userIdx = ? ";
        Object[] setUserAddressParams = new Object[]{patchUserReq.getUserAddress(), patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(setUserAddressQuery,setUserAddressParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select userIdx, userEmail, userPassword, userName, userPhoneNum, userFlag from User where USEREMAIL = ?";
        String getPwdParams = postLoginReq.getUserEmail();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userIdx"),
                        rs.getString("userEmail"),
                        rs.getString("userPassword"),
                        rs.getString("userName"),
                        rs.getString("userPhoneNum"),
                        rs.getString("userFlag")
                ),
                getPwdParams
        );

    }



}
