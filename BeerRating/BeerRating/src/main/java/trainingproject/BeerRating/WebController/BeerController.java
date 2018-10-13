package trainingproject.BeerRating.WebController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import trainingproject.BeerRating.Domain.Beer;
import trainingproject.BeerRating.Domain.BeerRepository;



@Controller
public class BeerController {
	/**  adding beerRepository **/
	@Autowired
	private BeerRepository beerRepository;
	
	/** returns a list of beers **/
	 @RequestMapping(value="/beerlist")
	    public String bookList(Model model) {	
	        model.addAttribute("beers", beerRepository.findAll());
	        return "beerlist";
	    }
	 /** returns a empty form for adding beers **/
	 @RequestMapping(value="/add")
		 public String addBeer(Model model) {
			model.addAttribute("beer", new Beer());
		 
		  return "addbeer";
	 }
	 /** saves the beer that was posted with the form**/
	 @RequestMapping(value="/save", method = RequestMethod.POST)
	 public String save(Beer beer) {
		 beerRepository.save(beer);
		 return "redirect:beerlist";
	 }
	 /** daletes a beer based on id **/
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteBeer(@PathVariable("id") Long beerId, Model model) {
	    	beerRepository.deleteById(beerId);
	        return "redirect:../beerlist"; 
	    }    
}
