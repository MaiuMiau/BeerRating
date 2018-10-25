package trainingproject.BeerRating.WebController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import trainingproject.BeerRating.Domain.SignupForm;
import trainingproject.BeerRating.Domain.User;
import trainingproject.BeerRating.Domain.UserRepository;



@Controller
public class UserController {
	@Autowired
    private UserRepository userRepository;
	
	 @RequestMapping(value = "signup")
	    public String addStudent(Model model){
	    	model.addAttribute("signupform", new SignupForm());
	        return "signup";
	    }	
	    
	    /**
	     * Create new user
	     * Check if user already exists & form validation
	     * 
	     * @param signupForm
	     * @param bindingResult
	     * @return
	     */
	 @RequestMapping(value = "saveuser", method = RequestMethod.POST)
	    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
	    	if (!bindingResult.hasErrors()) { // validation errors
	    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
		    		String pwd = signupForm.getPassword();
			    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();// antaa selkokielisen salasanan cryptattavaksi
			    	String hashPwd = bc.encode(pwd);//cryptattu salasana
		
			    	User newUser = new User(); //luodaan user olia
			    	newUser.setPasswordHash(hashPwd);//Asettetaan sille salasana
			    	newUser.setUsername(signupForm.getUsername());
			    	newUser.setRole("USER");
			    	if (userRepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
			    		userRepository.save(newUser);
			    	}
			    	else {
		    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
		    			return "signup";		    		
			    	}
	    		}
	    		else {
	    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
	    			return "signup";
	    		}
	    	}
	    	else {
	    		return "signup";// virhetilantessa palataan signuppiin
	    	}
	    	return "redirect:/login";    	
	    }    
}
