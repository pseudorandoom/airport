package com.fabian.airport.controller;

import com.fabian.airport.impl.PersistenceServiceImpl;
import com.fabian.airport.model.Plane;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PlaneController extends BaseController {
    @RequestMapping(value = "/planes", method = RequestMethod.GET)
    public String planes(Model model) {
        List<Plane> planes = PersistenceServiceImpl.query(Plane.class);
        model.addAttribute("planes", planes);
        return "landing";
    }

    @RequestMapping(value = "/planes", method = RequestMethod.POST)
    public String addPlane(Plane plane, Model model) {
        PersistenceServiceImpl.save(plane);
        List<Plane> planes = PersistenceServiceImpl.query(Plane.class);
        model.addAttribute("planes", planes);
        return "landing";
    }

    @ModelAttribute("plane")
    public Plane plane(){
        return new Plane();
    }
}
