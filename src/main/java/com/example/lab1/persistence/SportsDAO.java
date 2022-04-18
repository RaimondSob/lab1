package com.example.lab1.persistence;

import com.example.lab1.entities.Sport;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SportsDAO {
    @Inject
    private EntityManager em;

    public List<Sport> loadAll() {
        return em.createNamedQuery("Sport.findAll", Sport.class).getResultList();
    }

    public void persist(Sport sport) {
        em.persist(sport);
    }

    public void update(Sport sport) {
        em.merge(sport);
    }

    public Sport findById(Long id) {
        return em.find(Sport.class, id);
    }
}