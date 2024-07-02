package com.amit.BankingApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//@Configuration
//@EnableTransactionManagement
//public class ProjectConfig {
//    @Bean
//    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }
//}
