package com.geoalerte.geoalerte;

import com.geoalerte.geoalerte.models.ERole;
import com.geoalerte.geoalerte.models.Role;
import com.geoalerte.geoalerte.models.User;
import com.geoalerte.geoalerte.repositories.RoleRepository;
import com.geoalerte.geoalerte.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class GeoalerteApplication implements CommandLineRunner {
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(GeoalerteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// CREATION DES ROLES
		if (roleRepository.findAll().size() == 0) {
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
			roleRepository.save(new Role(ERole.ROLE_USER));
		}

		// CREATION DE UTILISATEUR
		if (userRepository.findAll().size() == 0) {
			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByName(ERole.ROLE_ADMIN).get();
			roles.add(role);
			User user = new User(
					"mary",
					"mary@gmail.com",
					encoder.encode("mary123")
			);
			user.setRoles(roles);
			userRepository.save(user);
		}
	}
}
