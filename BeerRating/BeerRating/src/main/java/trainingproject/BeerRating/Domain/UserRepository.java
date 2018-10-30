package trainingproject.BeerRating.Domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	/** Finds a list of beers for user **/
	//List<Beer> findBeersByUsername(String username);
	
	/** Finds user by name **/
	//List<User> findByName(@Param(value="name")String name);
	 
}
