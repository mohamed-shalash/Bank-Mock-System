package com.fawry.bank.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*@Configuration
@EnableWebSecurity
@RequiredArgsConstructor*/
public class SecurityConfiguration  {

/*    private final UserDetailsService userDetailsService;
    private final AuthenticationManagerBuilder auth;

    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user").password("password").roles("USER").build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin").password("password").roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user,admin);
    }*/

   /* @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) throws Exception {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();
        //auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
        return new InMemoryUserDetailsManager(user, admin);
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);//.passwordEncoder(getPasswordEncoder());
    }*/

  /*  @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF using the new approach
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/user").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/admin").hasRole("ADMIN")// Adjust paths as needed
                        .anyRequest().authenticated()
                ).formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/

}
