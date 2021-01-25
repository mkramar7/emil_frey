package com.markokramar.emilfrey.model;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cars")
@NamedQueries({
    @NamedQuery(name = "Cars.findAll", query = "SELECT c FROM Car c")
})
public class Car implements Serializable {
    private static final long serialVersionUID = -1677456857809574882L;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "model", nullable = false)
    private String model;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private CarCategory category;

    @PastOrPresent
    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @ManyToMany(mappedBy = "carsOfInterest", fetch = FetchType.EAGER)
    private Set<Lead> leads;

    public Long getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }

    public Set<Lead> getLeads() {
        return leads;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", category=" + category +
                ", manufacturingDate=" + manufacturingDate +
                ", leads=" + leads +
                '}';
    }
}
