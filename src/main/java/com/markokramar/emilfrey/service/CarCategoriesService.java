package com.markokramar.emilfrey.service;

import antlr.StringUtils;
import com.markokramar.emilfrey.model.Car;
import com.markokramar.emilfrey.model.CarCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CarCategoriesService {

    @PersistenceContext
    private EntityManager em;

    public List<CarCategory> getAll(String namePart) {
        TypedQuery<CarCategory> query = em.createNamedQuery("CarCategories.findAllWithName", CarCategory.class);
        return query.setParameter("name", namePart).getResultList();
    }

    public List<CarCategory> getAll() {
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
