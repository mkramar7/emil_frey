package com.markokramar.emilfrey.dao;

import com.markokramar.emilfrey.model.Lead;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LeadsDao {

    @PersistenceContext
    private EntityManager em;

    public List getAll() {
        return em.createNamedQuery("Leads.findAll", Lead.class).getResultList();
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
