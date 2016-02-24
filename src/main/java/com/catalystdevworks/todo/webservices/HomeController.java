package com.catalystdevworks.todo.webservices;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class HomeController {

	@Controller
	public class ExpenseTrackerGenericEndpoints {

		/**
	     * Controller to navigate to the home page
	     * @return String with location of html page to get
	     */
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String home() {
			return "/index.html";
		}
	}
	
}
