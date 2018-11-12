package trainingproject.BeerRating;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import trainingproject.BeerRating.Domain.Beer;
import trainingproject.BeerRating.Domain.BeerRepository;
import trainingproject.BeerRating.Domain.Rating;
import trainingproject.BeerRating.Domain.RatingRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RaitingRepositoryTest {
	
	
	@Autowired
	private BeerRepository Brepository;
	

	@Autowired
	private RatingRepository ratingRepository;
	

	
	@Test
	public void createNewRAting() {
		
	
		Rating rating = new Rating("23.12.2018", "BeerHouse", "Botle", "Sweet", 5, new Beer("Lösösen Lager", 4.0, "Lösösen Panimo", "Lager"));
		ratingRepository.save(rating);

		assertThat(rating.getId()).isNotNull();
		assertThat(rating).hasFieldOrPropertyWithValue("location", "BeerHouse");
	}
	
	@Test
	public void RatingCanBeDeleted() {
		
		
		Beer beer = new Beer("Lösösen Lager", 4.0, "Lösösen Panimo", "Lager");
		Brepository.save(beer);
	
		Rating rating = new Rating("18.11.2018", "BeerBeer",  "Can", "Sweet, Chocolate", 4,Brepository.findByName("Lösösen Lager").get(0));
		ratingRepository.save(rating);

		Long id = rating.getId();

		ratingRepository.deleteById(id);
		Optional<Rating> deletedRating = ratingRepository.findById(id);

		assertThat(deletedRating).isEmpty();

	}
	
	@Test
	public void canFinfRatingByBeer() {
		
		Beer beer = new Beer("Lösösen Lager", 4.0, "Lösösen Panimo", "Lager");
		Brepository.save(beer);
		
		Rating rating = new Rating("18.11.2018", "BeerBeer",  "Can", "Sweet, Chocolate", 4,Brepository.findByName("Lösösen Lager").get(0));
		ratingRepository.save(rating);

		
		List<Rating> ratings = ratingRepository.findByBeer(beer);
		
		assertThat(ratings).isNotEmpty();
		assertThat(ratings).hasSize(1);
		assertThat(ratings.get(0)).hasFieldOrPropertyWithValue("servingStyle", "Can");
		
	}
	

}
