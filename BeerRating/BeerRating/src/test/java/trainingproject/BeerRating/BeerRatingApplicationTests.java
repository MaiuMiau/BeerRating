package trainingproject.BeerRating;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import trainingproject.BeerRating.WebController.BeerController;
import trainingproject.BeerRating.WebController.RatingController;



/** Testing that your Bookcontroller is created**/

/** @author Maiju**/


@RunWith(SpringRunner.class)
@SpringBootTest
public class BeerRatingApplicationTests {

	@Autowired// alusta luo olion. ilman autowirea olio on null
    private BeerController beercontroller;
	
	@Autowired// alusta luo olion. ilman autowirea olio on null
    private RatingController ratingcontroller;
	
	@Test
	public void contextLoads() throws Exception{
		 assertThat(beercontroller).isNotNull();
	}
	
	@Test
	public void contextLoads2() throws Exception{
		 assertThat(ratingcontroller).isNotNull();
	}

}
