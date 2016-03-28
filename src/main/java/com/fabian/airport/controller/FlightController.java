package com.fabian.airport.controller;

import com.fabian.airport.helper.FlightHelper;
import com.fabian.airport.impl.PersistenceServiceImpl;
import com.fabian.airport.model.Flight;
import com.fabian.airport.model.Plane;
import com.fabian.airport.model.Route;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Controller
public class FlightController extends BaseController {

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public String flights(Model model) {
        List<Flight> flights = PersistenceServiceImpl.query(Flight.class);
        model.addAttribute("flights", flights);
        return "landing";
    }

    @RequestMapping(value = "/flights", method = RequestMethod.POST)
    public String addFlight(FlightHelper flightHelper, Model model) {
        Long routeId = flightHelper.getRouteId();
        Route route = PersistenceServiceImpl.find(Route.class, routeId);
        short departureTimeHour = flightHelper.getDepartureTimeHour();
        short departureTimeDay = flightHelper.getDepartureTimeDay();
        short departureTimeMonth = flightHelper.getDepartureTimeMonth();
        int departureTimeYear = flightHelper.getDepartureTimeYear();
        Date flightStart = getFlightStart(departureTimeHour, departureTimeDay, departureTimeMonth, departureTimeYear);
        Date flightEnd = getFlightEnd(departureTimeHour, departureTimeDay, departureTimeMonth, departureTimeYear, route);
        Long planeId = flightHelper.getPlaneId();
        Plane plane = PersistenceServiceImpl.find(Plane.class, planeId);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Flight> flightsQuery = PersistenceServiceImpl.query(
                String.format("FROM Flight AS f WHERE f.plane = %s AND f.departureTime BETWEEN '%s' AND '%s'",
                        plane.getId(),
                        simpleDateFormat.format(flightStart),
                        simpleDateFormat.format(flightEnd)));
        if (!flightsQuery.isEmpty()) {
            List<Flight> flights = PersistenceServiceImpl.query(Flight.class);
            model.addAttribute("flights", flights);
            return "landing";
        }

        Flight flight = new Flight();
        flight.setDepartureTime(flightStart);
        flight.setPlane(plane);
        flight.setRoute(route);
        PersistenceServiceImpl.save(flight);
        List<Flight> flights = PersistenceServiceImpl.query(Flight.class);
        model.addAttribute("flights", flights);
        return "landing";
    }

    private Date getFlightStart(short hour, short day, short month, int year) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR, hour);
        instance.set(Calendar.DAY_OF_MONTH, day);
        instance.set(Calendar.MONTH, month);
        instance.set(Calendar.YEAR, year);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    private Date getFlightEnd(short hour, short day, short month, int year, Route route) {
        Date flightStart = getFlightStart(hour, day, month, year);
        Calendar instance = Calendar.getInstance();
        instance.setTime(flightStart);
        instance.add(Calendar.MINUTE, route.getDuration().intValue());
        return instance.getTime();
    }

    @ModelAttribute("flightHelper")
    public FlightHelper flightHelper() {
        return new FlightHelper();
    }

    @ModelAttribute("dayOptions")
    public Map<String, String> dayOptions() {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        IntStream.range(1, 32).forEach(i -> builder.put(String.valueOf(i), String.valueOf(i)));
        return builder.build();
    }

    @ModelAttribute("hourOptions")
    public Map<String, String> hourOptions() {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        IntStream.range(0, 24).forEach(i -> builder.put(String.valueOf(i), String.valueOf(i)));
        return builder.build();
    }

    @ModelAttribute("monthOptions")
    public Map<String, String> monthOptions() {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        IntStream.range(1, 13).forEach(i -> builder.put(String.valueOf(i), String.valueOf(i)));
        return builder.build();
    }

    @ModelAttribute("yearOptions")
    public Map<String, String> yearOptions() {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        IntStream.range(2016, 2021).forEach(i -> builder.put(String.valueOf(i), String.valueOf(i)));
        return builder.build();
    }

    @ModelAttribute("routeIds")
    public List<Long> routeOptions() {
        List<Long> ids = PersistenceServiceImpl.query("SELECT r.id FROM Route as r");
        return ids;
    }

    @ModelAttribute("planeIds")
    public List<Long> planeOptions() {
        List<Long> ids = PersistenceServiceImpl.query("SELECT p.id FROM Plane as p");
        return ids;
    }
}
