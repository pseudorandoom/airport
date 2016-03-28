package com.fabian.airport.controller;

import com.fabian.airport.impl.PersistenceServiceImpl;
import com.fabian.airport.model.Passenger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PassengerController extends BaseController {

    @RequestMapping(value =  "/passengers", method = RequestMethod.GET)
    public String passengers(Model model) {
        List<Passenger> passengers = PersistenceServiceImpl.query(Passenger.class);
        model.addAttribute("passengers", passengers);
        return "landing";
    }

    @RequestMapping(value = "/passengers", method = RequestMethod.POST)
    public String addPassenger(Passenger passenger, Model model) {
        System.out.println("passenger = " + passenger);
        PersistenceServiceImpl.save(passenger);
        List<Passenger> passengers = PersistenceServiceImpl.query(Passenger.class);
        model.addAttribute("passengers", passengers);
        return "landing";
    }

    @ModelAttribute("passenger")
    public Passenger passenger() {
        return new Passenger();
    }
}
