package trainingproject.BeerRating.Domain;

import java.util.List;

import org.springframework.data.domain.Sort;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;




@RepositoryRestResource
public interface BeerRepository extends CrudRepository <Beer, Long>{
	
	/** Finds a Beer by beers name **/
	List<Beer> findByName(@Param(value="name")String name);
	
	/** Finds all beers for sorting **/
	List<Beer> findAll(Sort sort);
	
	List<Beer> findAll();
	
	/** Finds a list of beers for user **/
	List<Beer> findByUser(User user);
	
	
	


	
}
