package com.magister.garbagecollector.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.magister.garbagecollector.entities.Role;
import com.magister.garbagecollector.entities.User;
import com.magister.garbagecollector.entities.jpa.UserRepository;
import com.magister.garbagecollector.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.magister.garbagecollector.entities.jpa.RoleRepository;

@Slf4j
@Service
public class UserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	@Value("${blablacargo.roles.default}")
	private String defaultRole;
	
	@Value("${blablacargo.security.password.encoder}")
	private String passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	public boolean isAdmin(String username) {
		List<Role> roles = roleRepository.findByUsername(username);
		Optional<Role> maybeAdmin = roles.stream()
				.filter((Role role) -> role.getAuthority().equals("ROLE_ADMIN"))
				.findAny();
		return maybeAdmin.isPresent(); 
	}
	
	public boolean isAdmin(User user) {
		return isAdmin(user.getUsername());  
	}
	
	public List<User> getAll() {
		List<User> users = userRepository.findAll(); 
		return users;
	}
	
	public User getUser(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(username));
		return user;
	}
	
	public User createUser(User input, List<String> roles) {
		String storedPassword = String.format("{%s}%s", passwordEncoder, input.getPassword());
		input.setPassword(storedPassword);
		User savedUser = userRepository.save(input);
		String rolesString;
		if (roles == null) {
			rolesString = roleRepository.save(new Role(savedUser.getUsername(), defaultRole)).getAuthority();	
		} else {
			rolesString = roles.stream()
					.map((String roleString) -> {
						Role role = roleRepository.save(new Role(savedUser.getUsername(), roleString));
						return roleString;
					})
					.collect(Collectors.joining(", "));
		}
		log.info(String.format("Created new user with name '%s' and roles '%s'", savedUser.getUsername(), rolesString));
		return savedUser;
	};
	
	public User createUser(User input) {
		return createUser(input, null);
	}
	
	public User replaceUser(User input) {
		User user = userRepository.findByUsername(input.getUsername())
				.orElseThrow(() -> new UserNotFoundException(input.getUsername()));
		log.warn("replace: not implemented");
		return null;
	}

	public User updateUser(User input) {
		User user = userRepository.findByUsername(input.getUsername())
				.orElseThrow(() -> new UserNotFoundException(input.getUsername()));
		log.warn("update: not implemented");
		return null;
	}
	
	public void deleteUser(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(username));
		log.warn("delete: not implemented");
	}
	
	public User getLoggedInUser() {
		String username = getLoggedInUsername();
		User user = getUser(username);
		log.info(String.format("Got logged in user: %s", user.toString()));
		return user;
	}
	
	public String getLoggedInUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		log.info(String.format("Got logged in user name: %s", username));
		return username;
	}
}