package com.yonyou.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yonyou.common.annotation.RepositoryInterface;
import com.yonyou.recruit.listener.AppContextListener;

@EnableTransactionManagement
@Configuration
@EnableJpaRepositories(value="com.yonyou.recruit.repository",excludeFilters={
		             @ComponentScan.Filter(type=FilterType.ANNOTATION, value=RepositoryInterface.class)}) 
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.yonyou.recruit"}) 
public class WebApp {
	
	public static void main(String[] args) {  
		SpringApplication app = new SpringApplication(WebApp.class);
		app.addListeners(new AppContextListener());
        app.setWebEnvironment(true);
        app.run(args);
	}  
	
}
