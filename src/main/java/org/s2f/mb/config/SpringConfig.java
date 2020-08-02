package org.s2f.mb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@ComponentScan("org.s2f.mb")
@PropertySource("classpath:db.properties")
@EnableWebMvc
public class SpringConfig {

    /*@Autowired
    Environment environment;

    private final String URL = "url";
    private final String USER = "db.user";
    private final String DRIVER = "db.driver";
    private final String PASSWORD = "db.password";

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty(URL));
        driverManagerDataSource.setUsername(environment.getProperty(USER));
        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
        driverManagerDataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty(DRIVER)));
        return driverManagerDataSource;
    }*/
}
