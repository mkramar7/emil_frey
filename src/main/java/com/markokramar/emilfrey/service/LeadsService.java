package com.markokramar.emilfrey.service;

import com.markokramar.emilfrey.model.Car;
import com.markokramar.emilfrey.model.Lead;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class LeadsService {

    @PersistenceContext
    private EntityManager em;

    public List getAll() {
        return em.createNamedQuery("Leads.findAll", Lead.class).getResultList();
    }

    public List<Lead> getAll(String firstNamePart, String lastNamePart) {
        TypedQuery<Lead> query = em.createNamedQuery("Leads.findByFirstNameOrLastName", Lead.class)
                .setParameter("firstName", firstNamePart)
                .setParameter("lastName", lastNamePart);

        return query.getResultList();
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

    @Transactional
    public void delete(List<Long> ids) {
        Query query = em.createNamedQuery("Leads.deleteMultiple");
        query.setParameter("ids", ids);
        query.executeUpdate();
    }

}
