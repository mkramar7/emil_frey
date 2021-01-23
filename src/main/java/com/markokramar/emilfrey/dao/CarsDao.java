package com.markokramar.emilfrey.dao;

import com.markokramar.emilfrey.model.Car;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CarsDao {

    @PersistenceContext
    private EntityManager em;

    public List getAll() {
        return em.createNamedQuery("Cars.findAll", Car.class).getResultList();
    }

    public Car findById(Long id) {
        return em.find(Car.class, id);
    }

    public void update(Car car) {
        em.merge(car);
    }

    public void create(Car car) {
        em.persist(car);
    }

    public void delete(Car car) {
        if (!em.contains(car)) {
            car = em.merge(car);
        }

        em.remove(car);
    }
}
