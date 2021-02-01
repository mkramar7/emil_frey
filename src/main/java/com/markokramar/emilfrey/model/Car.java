package com.markokramar.emilfrey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "cars")
@NamedQueries({
    @NamedQuery(name = "Cars.findAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Cars.findAllWithMfrOrModel", query = "SELECT c FROM Car c WHERE c.manufacturer LIKE CONCAT('%', :name, '%') " +
            "OR c.model LIKE CONCAT('%', :name, '%')"),
    @NamedQuery(name = "Cars.deleteMultiple", query = "DELETE FROM Car c WHERE c.id IN (:ids)")
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

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CarCategory category;

    @Column(name = "model_year")
    private Integer modelYear;

    @JsonIgnore
    @ManyToMany(mappedBy = "carsOfInterest", fetch = FetchType.EAGER)
    private Set<Lead> leads;

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }

    public void setLeads(Set<Lead> leads) {
        this.leads = leads;
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
                ", modelYear=" + modelYear +
                ", leads=" + leads +
                '}';
    }
}
