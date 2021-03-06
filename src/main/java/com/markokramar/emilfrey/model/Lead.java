package com.markokramar.emilfrey.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "leads")
@NamedQueries({
        @NamedQuery(name = "Leads.findAll", query = "SELECT ld FROM Lead ld"),
        @NamedQuery(name = "Leads.findByFirstNameOrLastName", query = "SELECT ld FROM Lead ld WHERE ld.firstName LIKE CONCAT('%', :firstName, '%') " +
                "OR ld.lastName LIKE CONCAT('%', :lastName, '%')"),
        @NamedQuery(name = "Leads.deleteMultiple", query = "DELETE FROM Lead ld WHERE ld.id IN (:ids)")
})
public class Lead implements Serializable {
    private static final long serialVersionUID = -6951163276266605171L;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "lead_cars_of_interest",
            joinColumns = { @JoinColumn(name = "lead_id") },
            inverseJoinColumns = { @JoinColumn(name = "car_id") }
    )
    private Set<Car> carsOfInterest;

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Lead{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", carsOfInterest=" + carsOfInterest +
                '}';
    }
}
