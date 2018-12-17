package com.commis.framework.druid;

import com.alibaba.druid.pool.DruidDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Druid自动配置
 */
@Slf4j
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@EnableConfigurationProperties(DruidProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DruidAutoConfiguration {

    @Autowired
    private DruidProperties properties;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        if (properties.getInitialSize() > 0) {
            dataSource.setInitialSize(properties.getInitialSize());
            log.info("setInitialSize ---> {}", properties.getInitialSize());
        }
        if (properties.getMinIdle() > 0) {
            dataSource.setMinIdle(properties.getMinIdle());
            log.info("setInitialSize ---> {}", properties.getInitialSize());
        }
        if (properties.getMaxActive() > 0) {
            dataSource.setMaxActive(properties.getMaxActive());
            log.info("setMaxActive ---> {}", properties.getMaxActive());
        }
        if (properties.getTestOnBorrow() != null) {
            dataSource.setTestOnBorrow(properties.getTestOnBorrow());
            log.info("setTestOnBorrow ---> {}", properties.getTestOnBorrow());
        }
        if (properties.getMaxWait() > 0) {
            dataSource.setMaxWait(properties.getMaxWait());
            log.info("setMaxWait ---> {}", properties.getMaxWait());
        }
        if (properties.getTimeBetweenEvictionRunsMillis() > 0) {
            dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
            log.info("setTimeBetweenEvictionRunsMillis ---> {}", properties.getTimeBetweenEvictionRunsMillis());
        }
        if (properties.getMinEvictableIdleTimeMillis() > 0) {
            dataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
            log.info("setMinEvictableIdleTimeMillis ---> {}", properties.getMinEvictableIdleTimeMillis());
        }
        if (properties.getValidationQuery() != null) {
            dataSource.setValidationQuery(properties.getValidationQuery());
            log.info("setValidationQuery ---> {}", properties.getValidationQuery());
        }
        if (properties.getTestWhileIdle() != null) {
            dataSource.setTestWhileIdle(properties.getTestWhileIdle());
            log.info("setTestWhileIdle ---> {}", properties.getTestWhileIdle());
        }
        if (properties.getTestOnReturn() != null) {
            dataSource.setTestOnReturn(properties.getTestOnReturn());
            log.info("setTestOnReturn ---> {}", properties.getTestOnReturn());
        }
        if (properties.getPoolPreparedStatements() != null) {
            dataSource.setPoolPreparedStatements(properties.getPoolPreparedStatements());
            log.info("setPoolPreparedStatements ---> {}", properties.getPoolPreparedStatements());
        }
        if (properties.getMaxPoolPreparedStatementPerConnectionSize() > 0) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(
                properties.getMaxPoolPreparedStatementPerConnectionSize());
            log.info("setMaxPoolPreparedStatementPerConnectionSize ---> {}",
                properties.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if (properties.getFilters() != null) {
            try {
                dataSource.setFilters(properties.getFilters());
                log.info("setFilters ---> {}", properties.getFilters());
            } catch (SQLException e) {
                log.error("setInitialSize error");
                e.printStackTrace();
            }
        }
        if (properties.getConnectionProperties() != null) {
            dataSource.setConnectionProperties(properties.getConnectionProperties());
            log.info("setConnectionProperties ---> {}", properties.getConnectionProperties());
        }

        try {
            dataSource.init();
            log.info("dataSource.init()");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

}
