package com.yonyou.recruit.ctx;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.yonyou.common.dto.DtoConvertor;
import com.yonyou.common.dto.DtoConvertorFactory;

import freemarker.template.TemplateException;

@Configuration
@EnableWebMvc
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
     	registry.addResourceHandler("/html/**").addResourceLocations("classpath:/static/html/");
     	registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/bower_components/**").addResourceLocations("classpath:/static/bower_components/");
        super.addResourceHandlers(registry);
    }
    
    /*@Bean
	public FilterRegistrationBean siteMeshFilter() {
		FilterRegistrationBean fitler = new FilterRegistrationBean();
		MeshFilter siteMeshFilter = new MeshFilter();
		fitler.setFilter(siteMeshFilter);
		fitler.setOrder(1);
		return fitler;
	}
    
    @Bean
	public FilterRegistrationBean meshDecoderFilter() {
		FilterRegistrationBean fitler = new FilterRegistrationBean();
		SitemeshDecoder decoder = new SitemeshDecoder();
		fitler.setFilter(decoder);
		fitler.addUrlPatterns("*.ftl");
		fitler.setOrder(2);
		return fitler;
	}
    */
    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix(StringUtils.EMPTY );
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }
    
    @Bean
    public DtoConvertor dtoConvertor()
    {
    		return DtoConvertorFactory.getDefaultConvertor();
    }
 
   @Bean
    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPaths("classpath:templates", "src/main/resources/templates");
        factory.setDefaultEncoding("UTF-8");
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setConfiguration(factory.createConfiguration());
        return result;
    } 
}
