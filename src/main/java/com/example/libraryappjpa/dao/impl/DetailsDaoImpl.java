package com.example.libraryappjpa.dao.impl;

import com.example.libraryappjpa.dao.DetailsDao;
import com.example.libraryappjpa.entity.Details;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public class DetailsDaoImpl implements DetailsDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Details findById(int detailsId) {
        return entityManager.find(Details.class, detailsId);
    }

    @Override
    public Collection<Details> findAll() {
        return entityManager.createQuery("SELECT d FROM Details d", Details.class).getResultList();
    }

    @Override
    @Transactional
    public Details create(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Override
    @Transactional
    public void delete(int detailsId) {
        Details details = findById(detailsId);
        if (details != null) {
            entityManager.remove(details);
        }
    }
}
