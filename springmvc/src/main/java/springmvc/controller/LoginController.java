package springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import springmvc.model.Account;
import springmvc.model.User;
import springmvc.service.AccountService;
import springmvc.service.UserService;
import springmvc.utility.utility;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;	

	@RequestMapping("/login")
	public String showForm()
	{
		return "login";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
        // Validate user credentials
		
        User foundUser = userService.validateUser(email, password);

        if (foundUser!=null) {
        	List<Account> web = accountService.getUserWebsites(email);
     
            String websitesJson = new Gson().toJson(web);
        	model.addAttribute("websites", websitesJson);
        	model.addAttribute("userName",foundUser.getUsername());
        	model.addAttribute("userEmail",foundUser.getEmail());
            return "dashboard";
        } 
        else {
            // Failed login
        	model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
	
	
	@RequestMapping("/signup")
	public String signup(@ModelAttribute User user, Model model)
	{
		return "signup";
	}
	
	
	@RequestMapping(path ="/processform", method = RequestMethod.POST)
	public String processForm(@ModelAttribute User user, Model model)
	// Auto Mapping for field-names coming from JSP to Strings
	{	
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		newUser.setId(user.getId());
		
		model.addAttribute("user",newUser);
		user.setPassword(utility.generate_Hash(user.getPassword()));
		this.userService.createUser(user);
		return "dashboard";
	}
	
	@RequestMapping(path ="/register", method = RequestMethod.POST)
	public String processRegister(@ModelAttribute User user, Model model)
	{	
		if(!userService.isEmailUnique(user.getEmail())) {
        	model.addAttribute("error", "Email Already Exists!");
			return "login";
		}
			
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		newUser.setId(user.getId());
		
		model.addAttribute("user",newUser);
		user.setPassword(utility.generate_Hash(user.getPassword()));
		this.userService.createUser(user);
    	model.addAttribute("error", "Account successfully Created. Please Login!");
		return "login";
	}
}
