/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.atv1.login;

/**
 *
 * @author Ellen-
 */
import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public AppConfig() throws ClassNotFoundException {
        dataSource = JDBCUtil.getDataSource();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {
      

        builder.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email, senha, ativo"
                        + " from Usuario where email = ?")
                .authoritiesByUsernameQuery("select email, nome "
                        + "from Papel where email = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/teatro/**").hasRole("TEATRO")
                .antMatchers("/site/**").hasRole("SITE")                  
                .antMatchers(HttpMethod.POST,"/admin/insercaoSite").permitAll()
                .antMatchers(HttpMethod.POST,"/cadastroSite").permitAll()       
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .csrf().disable()                
                .httpBasic()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));                                                
    }
}
