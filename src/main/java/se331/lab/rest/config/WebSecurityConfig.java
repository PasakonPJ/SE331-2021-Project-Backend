package se331.lab.rest.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import se331.lab.rest.security.controller.JwtAuthenticationEntryPoint;
import se331.lab.rest.security.controller.JwtAuthenticationTokenFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                // Enable cors
                .cors().and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/auth/**",  "/refresh").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET,"/patients/**").permitAll()
                .antMatchers(HttpMethod.POST,"/patients/**").permitAll()
                .antMatchers(HttpMethod.POST,"/signup").permitAll()
                .antMatchers(HttpMethod.PUT,"/user/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/doctors/**").permitAll()
                .antMatchers(HttpMethod.POST,"/doctors/mypatient").permitAll()
                .antMatchers(HttpMethod.GET,"/user/**").permitAll()
                .antMatchers(HttpMethod.GET,"/users").permitAll()
                .antMatchers(HttpMethod.GET,"/comment/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/comment/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/comment/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/comment/edit").permitAll()
                .antMatchers(HttpMethod.POST,"/uploadFile").permitAll()
                .antMatchers(HttpMethod.GET,"/comment/only/{id}").permitAll()
                .anyRequest().authenticated();

        // Custom JWT based security filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.headers().frameOptions().disable();
        // disable page caching
        httpSecurity.headers().cacheControl();
    }


}