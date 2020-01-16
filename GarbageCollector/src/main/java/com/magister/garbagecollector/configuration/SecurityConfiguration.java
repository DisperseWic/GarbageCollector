package com.magister.garbagecollector.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final DataSource dataSource;

	@Autowired
	public SecurityConfiguration(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
               .dataSource(dataSource);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/h2/**").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/index/**").hasAnyRole("ADMIN", "USER")
				.antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
				.antMatchers("/cargoes/**").hasRole("ADMIN")
				.antMatchers("/users/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
            	.and()
            .csrf()
            	.disable()
            .headers().frameOptions().sameOrigin();
	}

}
