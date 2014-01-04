package org.esupportail.helpdesk.data.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.esupportail.helpdesk.data.web.controllers"})
public class MvcConfig extends WebMvcConfigurerAdapter {

}
