package org.ranasoftcraft.com.calender.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.Security;

@Configuration
@EnableWebSecurity
public class Config{


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
//                .formLogin().loginPage("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successForwardUrl("/")
//                .and()
//                .logout().logoutUrl("/login?logout=success");

        http.authorizeRequests(authorize-> authorize
                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                .requestMatchers("/login","/calendar","/webjars/**","/static/*","/user/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->{
            web.ignoring().requestMatchers("/login","/calendar","/webjars/**","/static/*").requestMatchers("/**");
        };
    }
}
