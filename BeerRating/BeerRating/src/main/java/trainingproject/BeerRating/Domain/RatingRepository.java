package trainingproject.BeerRating.Domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface RatingRepository extends CrudRepository <Rating, Long> {

	
	List<Rating> findByBeer(Beer beer);

	

}
