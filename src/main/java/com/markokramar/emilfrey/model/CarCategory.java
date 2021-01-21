package com.markokramar.emilfrey.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "car_categories")
public class CarCategory {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Car> cars;

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
