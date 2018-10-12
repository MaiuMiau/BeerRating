package trainingproject.BeerRating.WebController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import trainingproject.BeerRating.Domain.BeerRepository;



@Controller
public class BeerController {
	/**  tuodaan bookRepository controllerin käyttöön**/
	@Autowired
	private BeerRepository beerRepository;
	
	/** returns a list of beers **/
	 @RequestMapping(value="/beerlist")
	    public String bookList(Model model) {	
	        model.addAttribute("beers", beerRepository.findAll());
	        return "beerlist";
	    }

}
