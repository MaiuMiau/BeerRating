package trainingproject.BeerRating.Domain;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface RatingRepository extends CrudRepository <Rating, Long> {

	/** Finds a list of Ratings for Beer **/
	List<Rating> findByBeer(Beer beer);
		
	/** selects all rate values from beer and puts to array  **/
	@Query("select rate from Rating where beer =:beer")
	double[] findRatesByBeer(@Param(value="beer")Beer beer);
	
	
	
	
	
	

	

}
