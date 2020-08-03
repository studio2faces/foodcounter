package org.s2f.mb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan("org.s2f.mb")
@PropertySource("classpath:db.properties")
@EnableWebMvc
public class SpringConfig {

}
