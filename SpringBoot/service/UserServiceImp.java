package SpringBoot.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import SpringBoot.entites.Role;
import SpringBoot.entites.User;
import SpringBoot.repository.*;


@Service
public class UserServiceImp implements UserService{
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRepository roleRepository;
	 
		@Autowired
		BCryptPasswordEncoder encoder;



	@Override
	public void saveUser(User user) {
		user.setTx_user_password(encoder.encode(user.getTx_user_password()));
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		
	}

	@Override
	public void saveAdmin(User user) {

		user.setTx_user_password(encoder.encode(user.getTx_user_password()));
		Role userRole = roleRepository.findByRole("ADMIN_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	
		userRepository.save(user);
		
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		// Try to implement this method, as assignment.
		return false;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	
	}
}
