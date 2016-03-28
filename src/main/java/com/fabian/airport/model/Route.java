package com.fabian.airport.model;

import javax.persistence.*;

@Entity
@Table(name = "Route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long id;
    @Column(name = "duration_minutes", nullable = false)
    private Long duration;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private String departure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", duration=" + duration +
                ", destination='" + destination + '\'' +
                ", departure='" + departure + '\'' +
                '}';
    }
}
