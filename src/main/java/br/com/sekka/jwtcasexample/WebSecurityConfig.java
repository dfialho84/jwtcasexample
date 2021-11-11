package br.com.sekka.jwtcasexample;

import javax.management.loading.PrivateMLet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private JWTFilter jwtFilter;

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
        new AntPathRequestMatcher("/login"),
        new AntPathRequestMatcher("/logout"),
        new AntPathRequestMatcher("/public")
    );

    // private final TokenAuthenticatio

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //     UserDetails user = User.withDefaultPasswordEncoder().username("diego").password("password").roles("USER").build();
    //     manager.createUser(user);
    //     return manager;
    // }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PUBLIC_URLS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {        
        final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

        HttpStatusEntryPoint forbiddenEntryPoint = new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);

        http.authorizeRequests().requestMatchers(PROTECTED_URLS).authenticated()            
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling().defaultAuthenticationEntryPointFor(forbiddenEntryPoint, PROTECTED_URLS)
            .and()            
            .formLogin().disable()
            .logout().disable()
            .cors().disable()
            .httpBasic().disable();

            http.authenticationProvider(authenticationProvider);
            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }    
}
