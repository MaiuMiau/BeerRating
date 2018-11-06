package trainingproject.BeerRating.WebController;

import java.security.Principal;
import java.util.Collections;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import trainingproject.BeerRating.Domain.Beer;
import trainingproject.BeerRating.Domain.BeerRepository;
import trainingproject.BeerRating.Domain.Rating;
import trainingproject.BeerRating.Domain.RatingRepository;
import trainingproject.BeerRating.Domain.User;
import trainingproject.BeerRating.Domain.UserRepository;

@Controller
public class BeerController {
	/** adding beerRepository **/
	@Autowired
	private BeerRepository beerRepository;

	/** adding ratingRepository **/
	@Autowired
	private RatingRepository ratingRepository;

	/** adding userRepository **/
	@Autowired
	private UserRepository userRepository;

	/** RESTful service to get all ratings **/
	@RequestMapping(value = "/ratings", method = RequestMethod.GET)
	public @ResponseBody List<Rating> ratingListRest() {
		return (List<Rating>) ratingRepository.findAll();
	}

	/** RESTful service to get all beers **/
	@RequestMapping(value = "/beers", method = RequestMethod.GET)
	public @ResponseBody List<Beer> beerListRest() {
		return (List<Beer>) beerRepository.findAll();
	}
	
	/** RESTful service to get all users **/
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<User> userListRest() {
		return (List<User>) userRepository.findAll();
	}

	/** login form **/
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	/** "/" **/
	@RequestMapping(value = "/")
	public String line() {
		return "login";
	}

	/** returns frontpage with user info **/
	/*@RequestMapping(value = "/frontpage", method = RequestMethod.GET)
	public String Frontpaget(Model model, Principal principal) {

		// gets the username from logged in user
		String username = principal.getName();
		model.addAttribute("username", username);
		
		return "frontpage";
	}*/
	
	/** returns a list of all beers in database for admin **/
	@RequestMapping(value = "/beerlist")
	public String beerList(Model model) {
		
		// reverse List so that the newest is on top
		List<Beer> beers = beerRepository.findAll(new Sort(Sort.Direction.DESC, "beerId"));														
		model.addAttribute("beers", beers);
		return "beerlist";
	}
	
	/** returns a list of all users in database for admin **/
	@RequestMapping(value = "/userlist")
	public String userlist(Model model) {
		 model.addAttribute("users", userRepository.findAll());
		
		return "userlist";
	}

	/** returns a list of usersbeers **/
	@RequestMapping(value = "/userbeerlist")
	public String usersBeerList(Model model, Principal principal) {
		
		// gets the username from logged in user
				String username = principal.getName();
				model.addAttribute("username", username);
				
		User user = userRepository.findByUsername(username);
		model.addAttribute("user", user);

		List<Beer> beers = beerRepository.findByUser(user);
		// reverse List so that the newest is on top
		Collections.reverse(beers);
		model.addAttribute("beers", beers);
		
		// for adding new beer with Jqueryform
		model.addAttribute("beer", new Beer());
		
		/*long id = user.getId();
		System.out.println("TÄÄLLÄ TOIVOTTAVASTI OLSISI USER ID!!! " + id);*/

		return "userbeerlist";
	}

	
	/** saves users beer that was posted with the form from userbeerlist page**/
	@RequestMapping(value = "/save/{username}", method = RequestMethod.POST)
	public String savebeerforuser(Beer beer,Model model, @PathVariable("username") String username) {
		
		model.addAttribute("username", username);
		User user = userRepository.findByUsername(username);
		
		beer.setUser(user);
		beerRepository.save(beer);
			
		return "redirect:/userbeerlist" ;
	}

	/** deletes a beer based on id **/
	@RequestMapping(value = "/delete/{id}/{username}", method = RequestMethod.GET)
	public String deleteBeer(@PathVariable("id")Long beerId, Model model, @PathVariable("username") String username) {
		
		beerRepository.deleteById(beerId);
		return "redirect:/userbeerlist/" + username;
	}

	/** shows a beer and its ratings based on beerid **/
	@RequestMapping(value = "/showbeer/{id}", method = RequestMethod.GET)
	public String showRatings(@PathVariable("id") Long beerId, Model model) {
		
		
			Beer beer = beerRepository.findById(beerId).get();
			model.addAttribute("beer", beer);

			List<Rating> ratings = ratingRepository.findByBeer(beer);
			Collections.reverse(ratings);//reverse List of ratings so that the newest is on top
			model.addAttribute("ratings", ratings);
			
			
			model.addAttribute("rating", new Rating());// for adding new rating with Jqueryform

			// calculate average from beers ratings
			double[] allRates = ratingRepository.findRatesByBeer(beer);
			double sum = 0;
			
			for (int i = 0; i < allRates.length; i++) {
				sum = sum + allRates[i];

				double average = (sum / allRates.length);
				model.addAttribute("average", average);
			}
			
			return "beer";
		}
		
	

	/** edits a beer based on id **/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBeer(@PathVariable("id") Long beerId, Model model) {
		
		model.addAttribute("beer", beerRepository.findById(beerId));

		return "editbeer";
	}

	/**
	 * saves the beer that was edited with the editform and redirects to the beer
	 * details page
	 **/
	@RequestMapping(value = "/saveedited", method = RequestMethod.POST)
	public String update(@Valid Beer beer , BindingResult bindingResult, Model model) {
		
		 if (bindingResult.hasErrors()) {
	        	return "editbeer";
	        }
		 	
		 	beerRepository.save(beer);
		 	return "redirect:/showbeer/" + beer.getBeerId();
	    }
		
		

	/** saves the rating that was posted with the addrating form **/
	@RequestMapping(value = "/saveratings/{id}", method = RequestMethod.POST)
	public String save(@Valid Rating rating, BindingResult bindingResult, @PathVariable("id") Long beerId ) {
		
		if (bindingResult.hasErrors()) {
			return "redirect:/showbeer/" + beerId;
        }
		
			Beer beer = beerRepository.findById(beerId).get();
			rating.setBeer(beer);
			ratingRepository.save(rating);
			
		return "redirect:/showbeer/" + beer.getBeerId();
		
		
	}

	/** deletes rating based on id **/
	@RequestMapping(value = "/deleterating/{ratingid}/{beerid}", method = RequestMethod.GET)
	public String deleteRating(@PathVariable("ratingid") Long ratingid, @PathVariable("beerid") Long beerid,
			Model model) {
		ratingRepository.deleteById(ratingid);
		
		return "redirect:/showbeer/" + beerid;
	}

	/** returns a empty form for adding beers **/ // ei ehkä tarvita enää
	/*
	 * @RequestMapping(value = "/add") public String addBeer(Model model) {
	 * model.addAttribute("beer", new Beer()); return "addbeer"; }
	 */

	/** returns a empty form for adding rating to a beer **/ // ei ehkä tarvita enää
	/*
	 * @RequestMapping(value = "/addratings/{id}") public String addrating(Model
	 * model, @PathVariable("id") Long beerId) {
	 * 
	 * model.addAttribute("rating", new Rating()); Beer beer =
	 * beerRepository.findById(beerId).get(); model.addAttribute("beer", beer);
	 * return "addrating"; }
	 */

}
