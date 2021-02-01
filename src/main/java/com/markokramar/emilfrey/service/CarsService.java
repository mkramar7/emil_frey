package com.markokramar.emilfrey.service;

import com.markokramar.emilfrey.model.Car;
import com.markokramar.emilfrey.model.CarCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CarsService {

    @PersistenceContext
    private EntityManager em;

    public List getAll() {
        return em.createNamedQuery("Cars.findAll", Car.class).getResultList();
    }

    public List<Car> getAll(String namePart) {
        TypedQuery<Car> query = em.createNamedQuery("Cars.findAllWithMfrOrModel", Car.class);
        return query.setParameter("name", namePart).getResultList();
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
