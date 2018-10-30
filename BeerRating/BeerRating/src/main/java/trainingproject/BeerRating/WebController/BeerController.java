package trainingproject.BeerRating.WebController;

import java.util.Collections;
//import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import trainingproject.BeerRating.Domain.Beer;
import trainingproject.BeerRating.Domain.BeerRepository;
import trainingproject.BeerRating.Domain.Rating;
import trainingproject.BeerRating.Domain.RatingRepository;
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
	
	
	 //palauttaa tyhjän login lomakkeen(GET)
    // springalusta käsittelee loginin POST kun joku kirjautuu sisään
	/** login form **/
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
    
    /** "/" **/
    @RequestMapping(value="/")
    public String tuut() {	
        return "login";
    }	
    
    
    /** returns frontpage with user info **/
	/*@RequestMapping(value="/frontpage", method = RequestMethod.GET)
	public String Frontpaget(Model model) {
		
		model.addAttribute(User user);
		
		
		return "frontpage";
	}*/
    
    /** RESTful service to get all ratings **/
    @RequestMapping(value="/ratings", method = RequestMethod.GET)
    public @ResponseBody List<Rating> ratingListRest() {	
        return (List<Rating>) ratingRepository.findAll();
    } 
    
    /** RESTful service to get all beers **/
    @RequestMapping(value="/beers", method = RequestMethod.GET)
    public @ResponseBody List<Beer> beerListRest() {	
        return (List<Beer>) beerRepository.findAll();
    } 

	/** returns a list of beers **/
	@RequestMapping(value="/beerlist")
	public String beerList(Model model) {
		//model.addAttribute("beers", beerRepository.findAll());
		model.addAttribute("beer", new Beer());
		
		/** uusi metodi jolla saadan käänteinen järjestys**/
		 List<Beer>beers = beerRepository.findAll(new Sort(Sort.Direction.DESC, "beerId"));//Käänteinen järjestys. uusin ensin.
		 model.addAttribute("beers", beers);
		
		/** toinen metodi jolla voidaan saada käänteinen järjestys**/
		//List<Beer>beers = beerRepository.findAll();// piti tehdä metodi beerReposirtoryyn. ei toimi muuten
		 //Collections.reverse(beers);
		 	 
		return "beerlist";
	}

	/** returns a empty form for adding beers **/ // ei ehkä tarvita enää
	/*@RequestMapping(value = "/add")
	public String addBeer(Model model) {
		model.addAttribute("beer", new Beer());
		return "addbeer";
	}*/

	/** saves the beer that was posted with the form **/
	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public String save(Beer beer) {
		beerRepository.save(beer);
		return "redirect:beerlist";
	}

	/** deletes a beer based on id **/
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBeer(@PathVariable("id") Long beerId, Model model) {
		beerRepository.deleteById(beerId);
		return "redirect:../beerlist";
	}

	/** shows a beer and its ratings based on id **/
	@RequestMapping(value = "/showbeer/{id}", method = RequestMethod.GET)
	public String showRatings(@PathVariable("id") Long beerId, Model model) {
		Beer beer = beerRepository.findById(beerId).get();
		model.addAttribute("beer", beer);
		
		//model.addAttribute("ratings", ratingRepository.findByBeer(beer));
		List<Rating> ratings = ratingRepository.findByBeer(beer);
		Collections.reverse(ratings);
		model.addAttribute("ratings", ratings);
		model.addAttribute("rating", new Rating());// for adding new rating
				
						
		double[] allRates = ratingRepository.findRatesByBeer(beer);
		
		double sum = 0;
		for (int i = 0; i < allRates.length ; i++) {
			sum = sum + allRates[i];
		
			double average = (sum / allRates.length);
			model.addAttribute("average", average);
		}
		
		/*List<Rating> ratings = ratingRepository.findByBeer(beer);
		
		for (int i = 0; i < ratings.size() ; i++) {
			double summa = Rating.getRate();
			 summa = 0 + summa;
			 System.out.println("TÄSSÄ ARVOOOO " + summa);
		}*/
		return "beer";
	}

	/** edits a beer based on id **/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBeer(@PathVariable("id") Long beerId, Model model) {
		model.addAttribute("beer", beerRepository.findById(beerId));
		return "editbeer";
	}

	/** saves the beer that was edited with the editform and redirects to the beer details page **/
	@RequestMapping(value = "/saveedited", method = RequestMethod.POST)
	public String update(Beer beer) {
		beerRepository.save(beer);
		return "redirect:/showbeer/" + beer.getBeerId();
	}

	/** returns a empty form for adding rating to a beer **/ // ei ehkä tarvita enää
	/*@RequestMapping(value = "/addratings/{id}")
	public String addrating(Model model, @PathVariable("id") Long beerId) {

		model.addAttribute("rating", new Rating());
		Beer beer = beerRepository.findById(beerId).get();
		model.addAttribute("beer", beer);
		return "addrating";
	}*/

	/** saves the rating that was posted with the addrating form **/
	@RequestMapping(value = "/saveratings/{id}", method = RequestMethod.POST)
	public String save(Rating rating, @PathVariable("id") Long beerId) {
		Beer beer = beerRepository.findById(beerId).get();
		rating.setBeer(beer);
		ratingRepository.save(rating);
		return "redirect:/showbeer/" + beer.getBeerId();
	}
	
	/** deletes rating based on id **/
	@RequestMapping(value = "/deleterating/{ratingid}/{beerid}", method = RequestMethod.GET)
	public String deleteRating(@PathVariable("ratingid") Long ratingid, @PathVariable("beerid") Long beerid, Model model) {
		ratingRepository.deleteById(ratingid);
		return "redirect:/showbeer/"+ beerid;
	}

	
}
