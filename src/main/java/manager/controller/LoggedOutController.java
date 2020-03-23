package manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoggedOutController {
	
	@GetMapping("/logOut")
	public String getLoggedOutView(Model model)
	{
		return "views/loggedOutView";
	}

}
