package com.example.springbootdemo.config;

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
@MapperScan(basePackages = "com.example.springbootdemo.mapper.worker", sqlSessionFactoryRef = "WorkerSqlSessionFactory")
public class WorkerDataSourceConfigurer {

    @Bean("workerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.worker")
    public DataSource workerDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name="WorkerSqlSessionFactory")
    @Primary
    public SqlSessionFactory workerSqlSessionFactory(@Qualifier("workerDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/WorkerMapper.xml")
        );
        return bean.getObject();
    }

    @Bean("WorkerSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate workerSqlSessionTemplate(@Qualifier("WorkerSqlSessionFactory") SqlSessionFactory sessionFactory){
        return new SqlSessionTemplate(sessionFactory);
    }
}
