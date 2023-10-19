package com.example.libraryappjpa.dao;

import com.example.libraryappjpa.entity.AppUser;

import java.util.Collection;

public interface AppUserDao {

    AppUser findById(int getAppUserId);

    Collection<AppUser> findAll();

    AppUser create(AppUser appUser);

    AppUser update(AppUser appUser);

    void delete(int appUserId);
}
