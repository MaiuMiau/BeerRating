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
import trainingproject.BeerRating.Domain.User;
import trainingproject.BeerRating.Domain.UserRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class BeerRepositoryTest {

	
	@Autowired
	private BeerRepository repository;
	
	@Autowired
	private UserRepository Urepository;
	
	
	@Test
	public void createNewBeer() {
		
	
		Beer beer = new Beer("Lösösen Lager", 4.0, "Lösösen Panimo", "Lager");
		repository.save(beer);

		assertThat(beer.getBeerId()).isNotNull();
		assertThat(beer).hasFieldOrPropertyWithValue("name", "Lösösen Lager");
	}
	
	@Test
	public void shouldFindBeersIfRepositoryNotempty() {
		repository.save(new Beer("Hitachino Nest Dai Dai IPA", 6.0, "Kiuchi Brewery", "IPA", Urepository.findByUsername("mikko")));
		
		Iterable<Beer> beers = repository.findAll();
		assertThat(beers).isNotEmpty();
	}
	
	@Test
	public void beerCanBeDeleted() {
		
		User user1 = new User("mikko", "salasana", "USER");

		Beer beer = new Beer("Lösösen Lager", 4.0, "Lösösen Panimo", "Lager", user1);
		repository.save(beer);

		Long id = beer.getBeerId();

		repository.deleteById(id);
		Optional<Beer> deletedBeer = repository.findById(id);

		assertThat(deletedBeer).isEmpty();

	}
	
	
	
	
	@Test
	public void canFinfBeerByName() {
		
		List<Beer> beers = repository.findByName("Steamworks Pumpkin Ale");

		assertThat(beers).isNotEmpty();
		assertThat(beers).hasSize(1);
		assertThat(beers.get(0)).hasFieldOrPropertyWithValue("name", "Steamworks Pumpkin Ale");
		
	}
}
