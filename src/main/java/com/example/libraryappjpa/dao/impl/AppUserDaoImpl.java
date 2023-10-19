package com.example.libraryappjpa.dao.impl;

import com.example.libraryappjpa.dao.AppUserDao;
import com.example.libraryappjpa.entity.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class AppUserDaoImpl implements AppUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AppUser findById(int appUserId) {
        return entityManager.find(AppUser.class, appUserId);
    }

    @Override
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("SELECT a FROM AppUser a", AppUser.class).getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        return entityManager.merge(appUser);
    }

    @Override
    @Transactional
    public void delete(int appUserId) {
        AppUser appUser = findById(appUserId);
        if (appUser != null) {
            entityManager.remove(appUser);
        }
    }

    @Override
    @Transactional
    public void deleteWithDetails(int userId) {
        AppUser user = findById(userId);
        if (user != null) {
            if (user.getDetails() != null) {
                entityManager.remove(user.getDetails());
            }
            entityManager.remove(user);
        }
    }



}
