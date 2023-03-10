package com.murphy.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity //加了参数就不会爆红线了
public class SecurityConfig {

    @Resource
    UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        // 启用Security登录，使用默认登陆页面
        http.formLogin();

        // 自定义从数据库查询用户信息
        http.userDetailsService(userDetailsService);

        // 请求访问策略
        http.authorizeRequests()
                .antMatchers("/public/**").permitAll() //放行资源，自定义添加
                .anyRequest().authenticated(); //除以上放行资源外，其他全部拦截

        return http.build();
    }


    /**
     * 配置加密方法 - 否则会报错There is no PasswordEncoder mapped for the id “null”
     * @return
     */
    @Bean
    public BCryptPasswordEncoder encoding(){
        return new BCryptPasswordEncoder();
    }

}
