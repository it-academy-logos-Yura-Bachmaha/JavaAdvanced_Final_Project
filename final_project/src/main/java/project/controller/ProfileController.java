package project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.domain.User;
import project.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.findByEmail(auth.getName());
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView viewProfile(HttpServletRequest req) {
		req.setAttribute("mode", "VIEW_PROFILE");
		ModelAndView map = new ModelAndView("home");
		map.addObject("userViewer",  getCurrentUser());

		return map;
	}
	
}