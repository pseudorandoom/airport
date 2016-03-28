package com.fabian.airport.helper;

public class FlightHelper {
    private Long id;
    private int departureTimeYear;
    private short departureTimeMonth;
    private short departureTimeDay;
    private short departureTimeHour;
    private Long routeId;
    private Long planeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getPlaneId() {
        return planeId;
    }

    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }

    public int getDepartureTimeYear() {
        return departureTimeYear;
    }

    public void setDepartureTimeYear(int departureTimeYear) {
        this.departureTimeYear = departureTimeYear;
    }

    public short getDepartureTimeMonth() {
        return departureTimeMonth;
    }

    public void setDepartureTimeMonth(short departureTimeMonth) {
        this.departureTimeMonth = departureTimeMonth;
    }

    public short getDepartureTimeDay() {
        return departureTimeDay;
    }

    public void setDepartureTimeDay(short departureTimeDay) {
        this.departureTimeDay = departureTimeDay;
    }

    public short getDepartureTimeHour() {
        return departureTimeHour;
    }

    public void setDepartureTimeHour(short departureTimeHour) {
        this.departureTimeHour = departureTimeHour;
    }
}
