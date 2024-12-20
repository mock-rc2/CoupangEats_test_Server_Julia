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
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class MainService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MainDao mainDao;
    private final MainProvider mainProvider;
    private final JwtService jwtService;


    @Autowired
    public MainService(MainDao mainDao, MainProvider mainProvider, JwtService jwtService) {
        this.mainDao = mainDao;
        this.mainProvider = mainProvider;
        this.jwtService = jwtService;

    }
}