package com.duckduckgogogo.configurer;

import com.duckduckgogogo.domain.User;
import com.duckduckgogogo.services.ConfigInfoService;
import com.duckduckgogogo.services.UserService;
import com.duckduckgogogo.services.impl.Info;
import com.duckduckgogogo.utils.PasswordEncodeAssistant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ConfigInfoService configInfoService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        ( auth.userDetailsService(userDetailsService) )
                .passwordEncoder(
                new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        return PasswordEncodeAssistant.encode(((String) charSequence).toCharArray());
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String encodedPassword) {
                        return encodedPassword.equals(encode(charSequence));
                    }
                }
        );

        Info.serverIP = configInfoService.getServerIP();

    }

    /**
     *页面验证
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/style/**", "/javascript/**", "/fonts/**", "/api/**", "/console/**", "/images/**", "/voices/**", "/image/**", "/", "/RTSP/*", "/console/camera_management/get/*").permitAll() //
                .anyRequest().authenticated() //
                .and().formLogin().loginPage("/login").successForwardUrl("/logged").failureUrl("/?error").permitAll() //
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll(); //

        http.csrf().disable();

        super.configure(http);
    }

    @Service("userDetailsService")
    static class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UserService userService;

        /**
         * 登陆功能
         * */
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserDetails user = null;

            if (username != null) {
                username = username.trim().toLowerCase();
                if (!username.isEmpty()) {
                    user = userService.findByUsername(username);
                }
                if (user == null) {
                    user = userService.findByEmail(username);
                }
            }
            if(user != null){
                Info.adminEmail = ((User) user).getEmail();
                Info.adminName = username;
            }

            return user;
        }
    }
}
