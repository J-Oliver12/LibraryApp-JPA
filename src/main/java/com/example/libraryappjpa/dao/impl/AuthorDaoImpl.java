package com.example.libraryappjpa.dao.impl;

import com.example.libraryappjpa.dao.AuthorDao;
import com.example.libraryappjpa.entity.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author findById(int authorId) {
        return entityManager.find(Author.class, authorId);
    }

    @Override
    public Collection<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    @Override
    @Transactional
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        return entityManager.merge(author);
    }

    @Override
    @Transactional
    public void delete(int authorId) {
        Author author = findById(authorId);
        if (author != null) {
            entityManager.remove(author);
        }
    }

    @Override
    public Author findByFirstNameAndLastName(String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> root = criteriaQuery.from(Author.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("firstName"), firstName),
                        criteriaBuilder.equal(root.get("lastName"), lastName));

        List<Author> authors = entityManager.createQuery(criteriaQuery).getResultList();

        if (!authors.isEmpty()) {
            return authors.get(0);
        }

        return null;
    }
}
