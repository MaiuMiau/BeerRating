package trainingproject.BeerRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import trainingproject.BeerRating.WebController.UserDetailServiceImpl;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	    private UserDetailServiceImpl userDetailsService;	
	/*
	 @Override
	protected void configure(HttpSecurity http) throws Exception {
		  http
	        .authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
	        .and()
	        .authorizeRequests().antMatchers("/signup", "/saveuser").permitAll()
	        .and()
	        .authorizeRequests().anyRequest().authenticated()
	        .and()
	      .formLogin()
	          .loginPage("/login")
	          .defaultSuccessUrl("/beerlist")
	          .permitAll()
	          .and()
	      .logout()
	          .permitAll();
		}*/
	
	protected void configure(HttpSecurity http) throws Exception {
		 http
	        .authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
	        .and()
	        .authorizeRequests()
	        
	        	.antMatchers("/signup", "/saveuser").permitAll()
	        	.antMatchers( "/", "beerlist").permitAll()
	        	.antMatchers ("/delete/**", "/edit/**" ).hasAuthority("ADMIN") // vain admin voi poistaa tai editoida
	        	.anyRequest().authenticated() // millekään sivulle ei pääse ilman kirjautumista
	        	.and()
	          
		.formLogin()
			.loginPage("/login")//login urliin saa mennä kaikki
			.defaultSuccessUrl("/beerlist").permitAll()// sisäänkirjautumisen jälkeen siirtyy beerlistsivulle
			
		.and()
	      .logout()
	          .permitAll();//logout urliin saa mennä kaikki
		}
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	    }

}
