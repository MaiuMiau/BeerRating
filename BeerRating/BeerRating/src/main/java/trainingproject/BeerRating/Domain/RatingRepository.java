package trainingproject.BeerRating.Domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface RatingRepository extends CrudRepository <Rating, Long> {

	
	List<Rating> findByBeer(Beer beer);
	
	
	@Query("select rate from Rating where beer =:beer")
	double[] findRatesByBeer(@Param(value="beer")Beer beer);
	
	
	
	
	
	

	

}
