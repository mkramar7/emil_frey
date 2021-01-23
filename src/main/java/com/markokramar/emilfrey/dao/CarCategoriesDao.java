package com.markokramar.emilfrey.dao;

import com.markokramar.emilfrey.model.CarCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CarCategoriesDao {

    @PersistenceContext
    private EntityManager em;

    public List getAll() {
        return em.createNamedQuery("CarCategories.findAll", CarCategory.class).getResultList();
    }

    public CarCategory findById(Long id) {
        return em.find(CarCategory.class, id);
    }

    public void update(CarCategory carCategory) {
        em.merge(carCategory);
    }

    public void create(CarCategory carCategory) {
        em.persist(carCategory);
    }

    public void delete(CarCategory carCategory) {
        if (!em.contains(carCategory)) {
            carCategory = em.merge(carCategory);
        }

        em.remove(carCategory);
    }
}
