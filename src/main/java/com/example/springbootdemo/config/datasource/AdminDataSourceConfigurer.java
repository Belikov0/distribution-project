package com.example.springbootdemo.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;



@Configuration
@MapperScan(basePackages = "com.example.springbootdemo.mapper.admin", sqlSessionFactoryRef = "AdminSqlSessionFactory")
public class AdminDataSourceConfigurer {

    @Bean("adminDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.admin")
    public DataSource adminDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name="AdminSqlSessionFactory")
    public SqlSessionFactory adminSqlSessionFactory(@Qualifier("adminDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/AdminMapper.xml")
        );
        return bean.getObject();
    }

    @Bean("AdminSqlSessionTemplate")
    public SqlSessionTemplate adminSqlSessionTemplate(@Qualifier("AdminSqlSessionFactory") SqlSessionFactory sessionFactory){
        return new SqlSessionTemplate(sessionFactory);
    }
}
