package com.markokramar.emilfrey.service;

import com.markokramar.emilfrey.model.Car;
import com.markokramar.emilfrey.model.Lead;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class LeadsService {

    @PersistenceContext
    private EntityManager em;

    public List getAll() {
        return em.createNamedQuery("Leads.findAll", Lead.class).getResultList();
    }

    public List<Lead> getAll(String namePart) {
        TypedQuery<Lead> query = em.createNamedQuery("Leads.findAllWithName", Lead.class);
        return query.setParameter("name", namePart).getResultList();
    }

    public Lead findById(Long id) {
        return em.find(Lead.class, id);
    }

    public void update(Lead lead) {
        em.merge(lead);
    }

    public void create(Lead lead) {
        em.persist(lead);
    }

    public void delete(Lead lead) {
        if (!em.contains(lead)) {
            lead = em.merge(lead);
        }

        em.remove(lead);
    }

}
