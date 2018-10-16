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
import trainingproject.BeerRating.Domain.Rating;

@SpringBootApplication
public class BeerRatingApplication {
	private static final Logger log = LoggerFactory.getLogger(BeerRatingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BeerRatingApplication.class, args);
		
	}
	
	@Bean
	public CommandLineRunner bookDemo(BeerRepository beerRepository, RatingRepository ratingRepository) {
		return (args) -> {
			log.info("save a couple of beers");
			
			
			
			beerRepository.save(new Beer("Samuel Smith Organic Chocolate Stout", 5.0, "Samuel Smith Brewery", "sweet stout"));
			beerRepository.save(new Beer("Steamworks Pumpkin Ale", 6.5, "Steamworks", "ale"));	
			beerRepository.save(new Beer("Stone's Ginger Joe", 4.0, "Quantum Beverages", "special"));
			
			ratingRepository.save(new Rating("12.09.2018", "BeerHouse", "Botle", "Sweet", 5.0, beerRepository.findByName("Stone's Ginger Joe").get(0)));
			ratingRepository.save(new Rating("21.11.2018 ", "Hilpea Hauki", "Botle", "Malty", 3.25, beerRepository.findByName("Steamworks Pumpkin Ale").get(0)));	
			ratingRepository.save(new Rating("13.12.2018", "BeerBeer",  "Can", "Sweet, Chocolate", 4.5,beerRepository.findByName("Samuel Smith Organic Chocolate Stout").get(0)));
			
			
						
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
