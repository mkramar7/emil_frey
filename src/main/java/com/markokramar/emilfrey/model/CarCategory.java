package com.markokramar.emilfrey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "car_categories")
@NamedQueries({
    @NamedQuery(name = "CarCategories.findAll", query = "SELECT cc FROM CarCategory cc"),
    @NamedQuery(name = "CarCategories.findAllWithName", query = "SELECT cc FROM CarCategory cc WHERE cc.categoryName LIKE CONCAT('%', :name, '%')"),
    @NamedQuery(name = "CarCategories.deleteMultiple", query = "DELETE FROM CarCategory cc WHERE cc.id IN (:ids)")
})
public class CarCategory implements Serializable {
    private static final long serialVersionUID = 3480637050478827631L;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Car> cars;

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "CarCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
