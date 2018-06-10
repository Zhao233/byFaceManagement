//package com.example.demo.configure;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfigurationn extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .authorizeRequests()
//                    .antMatchers("/", "/style/**", "/javascript/**", "/fonts/**", "/api/**", "/console/**", "/images/**", "/voices/**").permitAll() //
//                    .anyRequest().authenticated() //
//                    .and()
//                .formLogin()
//                    .loginPage("/login_1")
//                    .successForwardUrl("/logged")
//                    .failureUrl("/?error")
//                    .permitAll().and()
//                .logout()
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/")
//                    .permitAll(); //
//
//        http.csrf().disable();
//
//        super.configure(http);
//    }
//
////    @Bean
////    @Override
////    public UserDetailsService userDetailsService(){
////
////        UserDetails user = User.withDefaultPasswordEncoder()
////                .username("username")
////                .password("password")
////                .roles("USER")
////                .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        ( auth.userDetailsService(userDetailsService) )
////                .passwordEncoder(
////                        new PasswordEncoder() {
////                            @Override
////                            public String encode(CharSequence charSequence) {
////                                return PasswordEncodeAssistant.encode(((String) charSequence).toCharArray());
////                            }
////
////                            @Override
////                            public boolean matches(CharSequence charSequence, String encodedPassword) {
////                                return encodedPassword.equals(encode(charSequence));
////                            }
////                        }
////                );
////    }
////
////    @Service("userDetailsService")
////    static class CustomUserDetailsService implements UserDetailsService {
////
////        @Autowired
////        private UserService userService;
////
////        /**
////         * 实现登陆s功能
////         * */
////        @Override
////        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////            UserDetails user = null;
////
////            if (username != null) {
////                username = username.trim().toLowerCase();
////                if (!username.isEmpty()) {
////                    user = userService.findByUsername(username);
////                }
////                if (user == null) {
////                    user = userService.findByEmail(username);
////                }
////            }
////
////            return user;
////        }
////    }
//}
