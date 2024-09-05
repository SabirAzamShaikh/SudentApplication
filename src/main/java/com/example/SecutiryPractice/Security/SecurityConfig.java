package com.example.SecutiryPractice.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Autowired
    DataSource dataSource;


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/signin").permitAll()
                        .anyRequest().authenticated());
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //    http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        http.headers(headers->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

@Bean
    public JdbcUserDetailsManager userDetailsService()
{
    UserDetails user1= User.withUsername("user1")
            .password(passwordEncoder().encode("password1"))//noop is used to encrypt the password
            .roles("USER")
            .build();

    UserDetails admin= User.withUsername("admin")
            .password(passwordEncoder().encode("adminPass")) .roles("ADMIN")
            .build();

    JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
    jdbcUserDetailsManager.createUser(user1);
    jdbcUserDetailsManager.createUser(admin);
    return jdbcUserDetailsManager;
//in Inmemoyuserdetailsmanager the data was stored just in the program file
    //but iwth jbdcuserdetailmanager we can store the details of user in external databases like MYSQL<,PGSQL
 //   return new InMemoryUserDetailsManager(user1, admin);
}
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }

}
