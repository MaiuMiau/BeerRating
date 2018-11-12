package trainingproject.BeerRating.WebController;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

@Controller
public class RatingController {
	
	/** adding beerRepository **/
	@Autowired
	private BeerRepository beerRepository;

	/** adding ratingRepository **/
	@Autowired
	private RatingRepository ratingRepository;

	
	/** RESTful service to get all ratings **/
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/ratings", method = RequestMethod.GET)
	public @ResponseBody List<Rating> ratingsListRest() {
		return (List<Rating>) ratingRepository.findAll();
	}
	
	/** RESTful service to get rating by id **/
	@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/rating/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Rating> findRatingRest(@PathVariable("id") Long ratingId) {	
    	return ratingRepository.findById(ratingId);
    }  
	
	/** saves the rating that was posted with the addrating form **/
	@RequestMapping(value = "/saveratings/{id}", method = RequestMethod.POST)
	public String save(@Valid Rating rating, BindingResult bindingResult, @PathVariable("id") Long beerId) {

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
	
}
