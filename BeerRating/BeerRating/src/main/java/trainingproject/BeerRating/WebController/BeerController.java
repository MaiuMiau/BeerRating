package trainingproject.BeerRating.WebController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class BeerController {
	/** adding beerRepository **/
	@Autowired
	private BeerRepository beerRepository;

	/** adding ratingRepository **/
	@Autowired
	private RatingRepository ratingRepository;
	
	
	
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
		model.addAttribute("beers", beerRepository.findAll());
		return "beerlist";
	}

	/** returns a empty form for adding beers **/
	@RequestMapping(value = "/add")
	public String addBeer(Model model) {
		model.addAttribute("beer", new Beer());
		return "addbeer";
	}

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
		model.addAttribute("ratings", ratingRepository.findByBeer(beer));
		/*List<Rating> ratings = ratingRepository.findByBeer(beer);
		
		for (int i = 0; i < ratings.size() ; i++) {
			double arvo = Rating.getRate()++;
			 //arvo = 0 + arvo;
			 System.out.println("TÄSSÄ ARVOOOO " + arvo);
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

	/** returns a empty form for adding rating to a beer **/
	@RequestMapping(value = "/addratings/{id}")
	public String addrating(Model model, @PathVariable("id") Long beerId) {

		model.addAttribute("rating", new Rating());
		Beer beer = beerRepository.findById(beerId).get();
		model.addAttribute("beer", beer);
		return "addrating";
	}

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

	/* /** saves the rating that was posted with the addrating form **/
	/*
	 * @RequestMapping(value = "/saveratings", method = RequestMethod.POST) public
	 * String save(Rating rating) {
	 * 
	 * System.out.println("TÄSSÄ LISTÄÄTY RATING" + rating);
	 * ratingRepository.save(rating); Beer beer = rating.getBeer();
	 * System.out.println("TÄSSÄ OLUT JOLLE RATING ON TEHTY" + beer); long beerId =
	 * beer.getBeerId();
	 * System.out.println("TÄSSÄ beerId OLUELLE JOLLE RATING ON TEHTY" + beerId);
	 * //model.addAttribute("beer", beer); return "redirect:/showbeer/" + beerId; }
	 */
}
