package trainingproject.BeerRating.Domain;


import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
	
	/** Finds a User by username **/
	User findByUsername(String username);
	
	/** Finds user by name **/
	//List<User> findByusername(@Param(value="username")String username);
}
