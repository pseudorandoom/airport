package com.fabian.airport.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseController {
    private static final List<NavLink> NAV_LINKS;

    static {
        List<NavLink> navLinks = new ArrayList<>(4);
        navLinks.add(new NavLink("/passengers", "Passengers"));
        navLinks.add(new NavLink("/routes", "Routes"));
        navLinks.add(new NavLink("/planes", "Planes"));
        navLinks.add(new NavLink("/flights", "Flights"));
        NAV_LINKS = Collections.unmodifiableList(navLinks);
    }

    @ModelAttribute("page")
    public String page(HttpServletRequest request) {
        return request.getServletPath();
    }

    @ModelAttribute("contextPath")
    public String contextPath(HttpServletRequest request) {
        return request.getContextPath();
    }

    @ModelAttribute("pageLinks")
    public List<NavLink> navLinks() {
        return NAV_LINKS;
    }

    public static class NavLink {
        private final String path;
        private final String label;

        public NavLink(String path, String label) {
            this.path = path;
            this.label = label;
        }

        public String getPath() {
            return path;
        }

        public String getLabel() {
            return label;
        }
    }
}
