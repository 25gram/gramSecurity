package com.its.gramsecurity.config;

import com.its.gramsecurity.config.oauth.LoginSuccessHandler;
import com.its.gramsecurity.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    //    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        // authentication manager (see below)
//    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/font/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/member/**").access("hasRole('ROLE_USER')")
                .antMatchers("/storyBoard/**").access("hasRole('ROLE_USER')")
                .antMatchers("/contents/**").access("hasRole('ROLE_USER')")
                .antMatchers("/main/main").access("hasRole('ROLE_USER')")
                .antMatchers("/main/").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/main/")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/main/main")
                    .usernameParameter("memberId")
                    .passwordParameter("memberPassword")
                    .failureUrl("/main/")
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/main/") // 로그아웃 성공시
                .and()
                    .oauth2Login()
                    .loginPage("/main/")
                    .successHandler(new LoginSuccessHandler("/main/main"))
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService);


    }
}