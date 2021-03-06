package pruebaopen.pruebaopen;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableOAuth2Sso
@EnableWebSecurity
@RestController
public class App extends WebSecurityConfigurerAdapter
{
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
  }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/**")
			.authorizeRequests()
				.antMatchers("/", "/login**", "/webjars/**", "/error**")
				.permitAll()
			.anyRequest()
        .authenticated()
      .and().logout().logoutSuccessUrl("/").permitAll()
      .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
