package br.com.dkzit.project;

import br.com.dkzit.project.model.Gender;
import br.com.dkzit.project.model.User;
import br.com.dkzit.project.model.UserProfile;
import br.com.dkzit.project.repository.UserProfileRepository;
import br.com.dkzit.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

@SpringBootApplication
public class SpringBootJpaOneToOneMappingExampleApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaOneToOneMappingExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Clean up database tables
		userProfileRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();

		// Create a User instance
		User user = new User("Alex", "Sandro", "alex_s_anastacio@hotmail.com", "password123");

		// Create a UserProfile instance
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1988, 10, 17);

		UserProfile userProfile = new UserProfile("41999887766", Gender.MALE, dateOfBirth.getTime());

		// Set child reference(userProfile) in parent entity (user)
		user.setUserProfile(userProfile);

		userProfile.setUser(user);

		//Save parent reference (which will save the child as well)
		userRepository.save(user);
	}




}
