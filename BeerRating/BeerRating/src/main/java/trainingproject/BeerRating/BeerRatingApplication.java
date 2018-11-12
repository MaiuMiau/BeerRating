package trainingproject.BeerRating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import trainingproject.BeerRating.Domain.Beer;
import trainingproject.BeerRating.Domain.BeerRepository;
import trainingproject.BeerRating.Domain.RatingRepository;
import trainingproject.BeerRating.Domain.User;
import trainingproject.BeerRating.Domain.UserRepository;
import trainingproject.BeerRating.Domain.Rating;

@SpringBootApplication
public class BeerRatingApplication {
	private static final Logger log = LoggerFactory.getLogger(BeerRatingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BeerRatingApplication.class, args);
		
	}
	
	@Bean
	public CommandLineRunner beerDemo(BeerRepository beerRepository, RatingRepository ratingRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("Save some test data");
			
			// Create users:  Mikko/nieminen, Maiju/nurmi, admin/admin, Bruno/retiisi
			User user1 = new User("Mikko", "$2a$10$CMcOTHrSE0uuk8wiOoFV.OltHTniVbtR96Kjl7gYQ3i/AXqfcXKHm", "USER");
			User user2 = new User("Maiju", "$2a$10$XajKI4SNSd8OfsewNtiv6.ivdfQybq3JAtA0PwDQ6M7ABe/yEveJS", "USER");
			User user3 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			User user4 = new User("Bruno", "$2a$04$/O/TWiM.5OowQnS2KKBxSebW2gBItxRT9cazQgVZtYRThVFAsyS1.", "USER");
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			
			beerRepository.save(new Beer("Samuel Smith Organic Chocolate Stout", 5.0, "Samuel Smith Brewery", "sweet stout", userRepository.findByUsername("Maiju")));
			beerRepository.save(new Beer("Steamworks Pumpkin Ale", 6.5, "Steamworks", "ale", userRepository.findByUsername("Maiju")));
			beerRepository.save(new Beer("Ballast Point Sour Wench Blackberry Ale", 7.0, "Ballast Point Brewing", "berliner weisse", userRepository.findByUsername("Maiju")));
			beerRepository.save(new Beer("Stone's Ginger Joe", 4.0, "Quantum Beverages", "special", userRepository.findByUsername("Mikko")));
			beerRepository.save(new Beer("Hitachino Nest Dai Dai IPA", 6.0, "Kiuchi Brewery", "IPA", userRepository.findByUsername("Mikko")));
		
			
			ratingRepository.save(new Rating("23.12.2018", "BeerHouse", "Botle", "Sweet", 5, beerRepository.findByName("Stone's Ginger Joe").get(0)));
			ratingRepository.save(new Rating("15.11.2018 ", "Hilpea Hauki", "Botle", "Malty", 3, beerRepository.findByName("Steamworks Pumpkin Ale").get(0)));	
			ratingRepository.save(new Rating("12.09.2018", "BeerPub",  "Can", "Sweet, Chocolate", 4,beerRepository.findByName("Samuel Smith Organic Chocolate Stout").get(0)));
			ratingRepository.save(new Rating("03.11.2018", "Bruuveri",  "Draft", "Creamy , Dark, Sweet ", 5,beerRepository.findByName("Samuel Smith Organic Chocolate Stout").get(0)));
			
		
			log.info("fetch all beers");
			for (Beer beer: beerRepository.findAll()) {
				log.info(beer.toString());
			}
			
			log.info("fetch all ratings");
			for (Rating rating: ratingRepository.findAll()) {
				log.info(rating.toString());
			}

		};
	}
}
