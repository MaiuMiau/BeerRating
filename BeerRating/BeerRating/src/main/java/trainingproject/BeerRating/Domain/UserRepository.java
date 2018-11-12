package trainingproject.BeerRating.Domain;


import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
	
	/** Finds a User by username**/
	User findByUsername(String username);
	
		 
}
