package trainingproject.BeerRating;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import trainingproject.BeerRating.Domain.User;
import trainingproject.BeerRating.Domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Test
	public void createNewUser() {

		User user = new User("Kirsi", "$2a$10$XajKI4SNSd8OfsewNtiv6.ivdfQybq3JAtA0PwDQ6M7ABe/yEveJS", "USER");
		userRepository.save(user);

		assertThat(user).isNotNull();
		assertThat(user).hasFieldOrPropertyWithValue("username", "Kirsi");
	}
	@Test
	public void canFindUserByUsername() {

		User user = new User("Kirsi", "$2a$10$XajKI4SNSd8OfsewNtiv6.ivdfQybq3JAtA0PwDQ6M7ABe/yEveJS", "USER");
		userRepository.save(user);
		
		String username = user.getUsername();
		User user2 = userRepository.findByUsername(username);
	
		assertThat(user2).isNotNull();
		assertThat(user2).hasFieldOrPropertyWithValue("username", "Kirsi");
		
	}
	
}
	
