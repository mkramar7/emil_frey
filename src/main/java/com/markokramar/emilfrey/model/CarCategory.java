package com.markokramar.emilfrey.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "car_categories")
@NamedQueries({
    @NamedQuery(name = "CarCategories.findAll", query = "SELECT cc FROM CarCategory cc")
})
public class CarCategory implements Serializable {
    private static final long serialVersionUID = 3480637050478827631L;

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

    @Override
    public String toString() {
        return "CarCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", cars=" + cars +
                '}';
    }
}
