package photoalbum.app.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@Autowired
	MessageSource messageSource;
	
	
	@GetMapping("/")
	public String index(Model model, Locale locale) {
		
		model.addAttribute("title", messageSource.getMessage("home.title",null, locale));
		
		return "index";
	}

}