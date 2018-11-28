package com.sample.LibraryService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.sample.LibraryService.filters.JwtFilter;

@SpringBootApplication
@MapperScan("com.sample.LibraryService.dao")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class LibraryServiceApplication {
	
	/**
	 * Adding JwtFilter to validate token passed in request
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new JwtFilter());
		bean.addUrlPatterns("/library/*");
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryServiceApplication.class, args);
	}
}
