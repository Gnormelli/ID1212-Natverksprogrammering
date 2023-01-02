package com.ID1212.ID1212_Project_Intelij.Config;

import com.ID1212.ID1212_Project_Intelij.DataAccess.UserRepository;
import com.ID1212.ID1212_Project_Intelij.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Bean
    PasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * This is running
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    /**
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) throw new BadCredentialsException("Invalid username or password");

        return new UsernamePasswordAuthenticationToken(
                user, password, user.getAuthorities()
        );
    }
    */

    // Does the Authentication
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setPasswordEncoder(bcryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userService);
        return daoAuthenticationProvider;
    }

    // Does the Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
//                .cors().disable()
                .authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/addrole").permitAll()
                .antMatchers("/api/queuePost").permitAll()
                .antMatchers("/postQueue").permitAll()
                .antMatchers("/getQueue").permitAll()
                .antMatchers("/createUser").permitAll()
                .antMatchers("/admin").permitAll()
                .antMatchers("/user").permitAll()
                .antMatchers("/perform_login").permitAll()
                .antMatchers("/create_user").permitAll()
                .antMatchers("/get_profile_picture").permitAll()
                .antMatchers("/conversations_by_userID").permitAll()
                .antMatchers("/group_member_by_conId").permitAll()
                .antMatchers("/update_profile_picture").permitAll()
                .antMatchers("/update_membership").permitAll()
                .antMatchers("/send_message").permitAll()
                .antMatchers("/get_profile_picture").permitAll()
                .antMatchers("/get_message").permitAll()
                .antMatchers("/get_all_conversations").permitAll()
                .antMatchers("/get_posts").permitAll()
                .antMatchers("/send_post").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and().httpBasic();
    }


    /**
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS)
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .build();
    }
    */
}
