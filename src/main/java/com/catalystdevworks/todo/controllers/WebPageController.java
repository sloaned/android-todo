package com.catalystdevworks.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by g on 2/25/16.
 */
@Controller
public class WebPageController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String home() {
        return "/index.html";
    }
}
