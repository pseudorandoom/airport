package com.fabian.airport.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LandingController extends BaseController {
    private static final Logger _log = LoggerFactory.getLogger(LandingController.class);

    @RequestMapping(value = {"/"})
    public String landingPage(Model model, HttpServletRequest request) {
        _log.info("on LandingController ---------");
        System.out.println("on LandingController ---------");
        return "landing";
    }
}
