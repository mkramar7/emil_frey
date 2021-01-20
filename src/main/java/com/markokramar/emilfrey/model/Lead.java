package com.markokramar.emilfrey.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "leads")
public class Lead {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
