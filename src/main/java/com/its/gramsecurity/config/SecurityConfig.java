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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


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

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/font/**","/img/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/member/**").access("hasRole('ROLE_USER')")
                .antMatchers("/storyBoard/**").access("hasRole('ROLE_USER')")
                .antMatchers("/contents/**").access("hasRole('ROLE_USER')")
                .antMatchers("/board/**").access("hasRole('ROLE_USER')")
                .antMatchers("/comment/**").access("hasRole('ROLE_USER')")
                .antMatchers("/follow/**").access("hasRole('ROLE_USER')")
                .antMatchers("/msg/**").access("hasRole('ROLE_USER')")
                .antMatchers("/home/**").permitAll()
                .and()
                .formLogin()
                    .loginPage("/home/")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/main/main")
                    .usernameParameter("loginId")
                    .passwordParameter("memberPassword")
                    .failureUrl("/home/")
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/main/") // 로그아웃 성공시
                .and()
                    .oauth2Login()
                    .loginPage("/home/")
                    .successHandler(new LoginSuccessHandler("/main/main"))
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService);


    }

//    @Bean
//    HttpSessionCsrfTokenRepository sessionCsrfTokenRepository(){
//        HttpSessionCsrfTokenRepository csrfTokenRepository=new HttpSessionCsrfTokenRepository();
//        csrfTokenRepository.setHeaderName("X-CSRF_TOKEN");
//        csrfTokenRepository.setParameterName("_csrf");
//        csrfTokenRepository.setSessionAttributeName("CSRF-TOKEN");
//        return csrfTokenRepository;
//    }
//    @Bean
//    CookieCsrfTokenRepository csrfTokenRepository(){
//        CookieCsrfTokenRepository csrfTokenRepository=new CookieCsrfTokenRepository();
//        csrfTokenRepository.setCookieHttpOnly(false);
//        csrfTokenRepository.setHeaderName("X-CSRF-TOKEN");
//        csrfTokenRepository.setParameterName("_csrf");
//        csrfTokenRepository.setCookieName("XSRF-TOKEN");
//        return csrfTokenRepository;
//    }

}