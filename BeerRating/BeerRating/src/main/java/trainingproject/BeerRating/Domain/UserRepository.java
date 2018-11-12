package trainingproject.BeerRating.Domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
	
	/** Finds a User by username**/
	User findByUsername(String username);
	
	/** Finds a list of beers for user **/
	List<Beer> findBeersByUsername(String username);
	 
}
