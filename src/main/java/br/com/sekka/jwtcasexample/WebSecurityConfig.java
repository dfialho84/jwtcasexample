package br.com.sekka.jwtcasexample;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import br.com.sekka.jwtcasexample.jwtsecurity.DefaultJWTTokenParser;
import br.com.sekka.jwtcasexample.jwtsecurity.IpExtractor;
import br.com.sekka.jwtcasexample.jwtsecurity.JWTAuthenticationProvider;
import br.com.sekka.jwtcasexample.jwtsecurity.JWTFilter;
import br.com.sekka.jwtcasexample.jwtsecurity.JWTTokenParser;
import br.com.sekka.jwtcasexample.utils.HttpUtils2;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Value("${jwt.key}")
    private String secretKey;

    // @Autowired
    // private JWTFilter jwtFilter;

    // @Autowired
    // private JwtAuthenticationProvider authenticationProvider;

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
    
    // @Bean
    // @Override
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    //     return super.authenticationManagerBean();
    // }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PUBLIC_URLS);
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {        
    //     final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

    //     HttpStatusEntryPoint forbiddenEntryPoint = new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);

    //     http.authorizeRequests().requestMatchers(PROTECTED_URLS).authenticated()            
    //         .and()
    //         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //         .and()
    //         .exceptionHandling().defaultAuthenticationEntryPointFor(forbiddenEntryPoint, PROTECTED_URLS)
    //         .and()            
    //         .formLogin().disable()
    //         .logout().disable()
    //         .cors().and()
    //         .httpBasic().disable();

    //         http.authenticationProvider(authenticationProvider);
    //         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);            
    // }    


    private JWTTokenParser JwtParser() {        
        JWTTokenParser parser = new DefaultJWTTokenParser(secretKey);
        return parser;
    }

    private AuthenticationProvider authenticationProvider() {
        JWTAuthenticationProvider provider = new JWTAuthenticationProvider(JwtParser());
        return provider;
    }

    private IpExtractor ipExtractor() {
        HttpUtils2 u2 = new HttpUtils2();
        return u2;
    }

    private Filter jwtFilter() {
        JWTFilter filter = new JWTFilter(ipExtractor());
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return filter;
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
            .cors().and()
            .httpBasic().disable();

            http.authenticationProvider(authenticationProvider());
            http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);            
    }    
}
