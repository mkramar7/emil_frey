package com.markokramar.emilfrey.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "leads")
@NamedQueries({
        @NamedQuery(name = "Leads.findAll", query = "SELECT ld FROM Lead ld")
})
public class Lead {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "lead_cars_of_interest",
            joinColumns = { @JoinColumn(name = "lead_id") },
            inverseJoinColumns = { @JoinColumn(name = "car_id") }
    )
    private Set<Car> carsOfInterest;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Car> getCarsOfInterest() {
        return carsOfInterest;
    }

    public void setCarsOfInterest(Set<Car> carsOfInterest) {
        this.carsOfInterest = carsOfInterest;
    }

    public void addCarOfInterest(Car carOfInterest) {
        if (this.carsOfInterest == null) {
            this.carsOfInterest = new HashSet<>();
        }

        this.carsOfInterest.add(carOfInterest);
    }
}
