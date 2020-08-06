package org.s2f.mb.config;

import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;


@Configuration
@ComponentScan("org.s2f.mb")
@PropertySource("classpath:db.properties")
@EnableWebMvc
public class SpringConfig {



}
