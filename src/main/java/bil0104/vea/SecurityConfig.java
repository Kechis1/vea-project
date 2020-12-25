package bil0104.vea;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void initSecurity(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().withUser("david").password("{noop}david").roles("ADMIN");
        builder.inMemoryAuthentication().withUser("student").password("{noop}student").roles("STUDENT");
        builder.authenticationProvider(new AuthenticationProvider() {

            @Override
            public boolean supports(Class<?> clazz) {
                return clazz.equals(UsernamePasswordAuthenticationToken.class);
            }

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                if (authentication.getName().startsWith("aaa")
                        && authentication.getCredentials().toString().startsWith("bb")) {
                    List<GrantedAuthority> grantedAuths = new ArrayList<>();
                    grantedAuths.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
                    Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getName(), null,
                            grantedAuths);
                    return auth;
                }
                return null;
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests().antMatchers("/**").authenticated().and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll().invalidateHttpSession(true).logoutSuccessUrl("/login?logout").permitAll()
                .and().exceptionHandling().accessDeniedPage("/403");*/


        ;
    }

}