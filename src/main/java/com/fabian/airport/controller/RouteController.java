package com.fabian.airport.controller;

import com.fabian.airport.impl.PersistenceServiceImpl;
import com.fabian.airport.model.Route;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RouteController extends BaseController {

    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    public String routes(Model model) {
        List<Route> routes = PersistenceServiceImpl.query(Route.class);
        model.addAttribute("routes", routes);
        return "landing";
    }

    @RequestMapping(value = "/routes", method = RequestMethod.POST)
    public  String addRoute(Route route, Model model) {
        PersistenceServiceImpl.save(route);
        System.out.println("route = " + route);
        List<Route> routes = PersistenceServiceImpl.query(Route.class);
        model.addAttribute("routes", routes);
        return "landing";
    }

    @ModelAttribute("route")
    public Route route() {
        return new Route();
    }
}
