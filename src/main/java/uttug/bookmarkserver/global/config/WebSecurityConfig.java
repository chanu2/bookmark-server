package uttug.bookmarkserver.global.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uttug.bookmarkserver.global.security.CustomAuthenticationEntryPoint;
import uttug.bookmarkserver.global.security.JwtAuthFilter;
import uttug.bookmarkserver.global.security.JwtUtil;

@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 X
                .and()
                .authorizeRequests()
                .antMatchers("/test/fcm/**", "/user/checkUnique",
                        "/user/signUp",
                        "/user/signUp2",
                        "/user/signIn2",
                        "/user/asd",
                        "/user/signIn").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/guest/**").hasRole("GUEST")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtUtil),
                        UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }
}