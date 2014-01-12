package tn.tunisietelecom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tn.tunisietelecom.event.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/load-view", method = RequestMethod.GET)
	public String loadView(Model model) {
		// model.addAttribute("command", new LoginBean());
		return "telecom.login";
	}

	@RequestMapping(value = "/load-view", params = "error", method = RequestMethod.GET)
	public String loadViewWithError(Model model) {
		model.addAttribute("error", true);
		return "telecom.login";
	}
}
