package com.fabian.airport.model;

import javax.persistence.*;

@Entity
@Table(name = "Plane")
public class Plane {
    @Id
    @Column(name = "plane_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long capacity;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String producer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
