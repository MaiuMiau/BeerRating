package trainingproject.BeerRating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import trainingproject.BeerRating.Domain.Beer;
import trainingproject.BeerRating.Domain.BeerRepository;

@SpringBootApplication
public class BeerRatingApplication {
	private static final Logger log = LoggerFactory.getLogger(BeerRatingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BeerRatingApplication.class, args);
		
	}
	
	@Bean
	public CommandLineRunner bookDemo(BeerRepository beerRepository) {
		return (args) -> {
			log.info("save a couple of books");
			
			
			
			beerRepository.save(new Beer("Samuel Smith Organic Chocolate Stout", 5.0, "Samuel Smith Brewery", "sweet stout"));
			beerRepository.save(new Beer("Steamworks Pumpkin Ale", 6.5, "Steamworks", "ale"));	
			beerRepository.save(new Beer("Stone's Ginger Joe", 4.0, "Quantum Beverages", "special"));
			
			;
						
			log.info("fetch all beers");
			for (Beer beer: beerRepository.findAll()) {
				log.info(beer.toString());
			}

		};
	}
}
