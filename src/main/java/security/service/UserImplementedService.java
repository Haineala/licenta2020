package security.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manager.entity.AppUser;
import manager.entity.Role;
import repository.AppUserRepository;
import security.config.EncrytedPasswordUtils;

@Service
@Transactional
public class UserImplementedService implements UserDetailsService {
	
	@Autowired
	private AppUserRepository appUserRepo;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser appUser = getUserByNameIfAccountIsEnabled(email);
		if (appUser == null) {
			LOGGER.error("User " + appUser + " was not found in the database");
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		LOGGER.info("Found user " + appUser.getEmail());
		
		List<Role> roleNames = this.getUserRoles(appUser);
		LOGGER.info("The user " + appUser.getEmail() + " has the roles " + roleNames);

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		List<Role> roles = new ArrayList<>();

		if (roleNames != null) {
			for (Role role : roleNames) {
				roles.add(role);
				LOGGER.info("am adaugat rolul in lista de roluri " + roles);
				GrantedAuthority auth = new SimpleGrantedAuthority(role.getRole().toString());
				grantList.add(auth);
				LOGGER.info("Am aduagat in lista de autoritatitati autoritatea " + auth);
			}
		}
		
		for (GrantedAuthority auth : grantList) {
			LOGGER.info("autoritatea pentru userul care incearca sa se autentifice este" + auth);
			
			if (auth.getAuthority().equals("ROLE_TEACHER") || auth.getAuthority().equals("ROLE_STUDENT")) 
			{
				LOGGER.info("Arunc exceptie pentru ca utilizatorul nu are rolul necesar pentru logare");
				throw new UsernameNotFoundException("User " + email + "nu are rolul necesar pentru logare");
			}
		}

		UserDetails userDetails = (UserDetails) new User(appUser.getEmail(),
		EncrytedPasswordUtils.encrytePassword(appUser.getPassword()), grantList);
		LOGGER.info(userDetails.toString());
		return userDetails;
	}

	private AppUser getUserByNameIfAccountIsEnabled(String email) {
		Iterable<AppUser> userList = appUserRepo.findAll();
		for (AppUser appUser : userList) {
			if (appUser.getEmail().equals(email))
				if (appUser.isEnabled() == true)
					return appUser;
		}
		return null;
	}

	private List<Role> getUserRoles(AppUser appUser) {
		if (appUser != null)
			return appUser.getRoles();
		return null;
	}
}