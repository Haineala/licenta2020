package security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/","/login", "/logout").permitAll();	
		http.authorizeRequests().antMatchers("/index").authenticated();
		http.authorizeRequests().and().formLogin()//
		// Submit URL of login page.
		.loginProcessingUrl("/j_spring_security_check") // Submit URL
		.loginPage("/login")//
		.defaultSuccessUrl("/index")//
		.failureUrl("/login?error=true")//
		.usernameParameter("email")//
		.passwordParameter("password")
		// Config for Logout Page
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful")
		.deleteCookies("JSESSIONID", "JWT").clearAuthentication(true).invalidateHttpSession(true);
		/*.deleteCookies("auth_code", "JSESSIONID").clearAuthentication(true).invalidateHttpSession(true);*/

	}
}