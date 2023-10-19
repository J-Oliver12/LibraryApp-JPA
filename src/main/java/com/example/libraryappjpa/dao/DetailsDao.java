package com.example.libraryappjpa.dao;

import com.example.libraryappjpa.entity.Details;

import java.util.Collection;

public interface DetailsDao {

    Details findById(int detailsId);

    Collection<Details> findAll();

    Details create(Details details);

    Details update(Details details);

    void delete(int detailsId);



}
