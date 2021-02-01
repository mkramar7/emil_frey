package com.markokramar.emilfrey.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car_categories")
@NamedQueries({
    @NamedQuery(name = "CarCategories.findAll", query = "SELECT cc FROM CarCategory cc"),
    @NamedQuery(name = "CarCategories.findAllWithName", query = "SELECT cc FROM CarCategory cc WHERE cc.categoryName LIKE CONCAT('%', :name, '%')")
})
public class CarCategory implements Serializable {
    private static final long serialVersionUID = 3480637050478827631L;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

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

    @Override
    public String toString() {
        return "CarCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
