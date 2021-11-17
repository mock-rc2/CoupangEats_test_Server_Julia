package com.example.demo.src.search;


import com.example.demo.src.search.model.*;
import com.example.demo.src.store.model.PostOrderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SearchDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createSearch(PostSearchReq postSearchReq){
        String createSearchQuery = "insert into Search (userIdx, searchContents) VALUES (?,?)";
        Object[] createSearchParams = new Object[]{postSearchReq.getUserIdx(), postSearchReq.getSearchContents()};
        this.jdbcTemplate.update(createSearchQuery, createSearchParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }
}