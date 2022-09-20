package com.noirix.repository.hibernate;

import com.noirix.domain.User;
import com.noirix.domain.hibernate.HibernateUser;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class HibernateUserInterfaceImpl implements HibernateUserInterface {

    //private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public HibernateUser findById(Long id) {
        return null;
    }

    @Override
    public Optional<HibernateUser> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<HibernateUser> findAll() {
//        try (Session session = sessionFactory.openSession()) {
//
//            return session.createQuery("select hb from HibernateUser hb", HibernateUser.class).getResultList();
//        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select hb from HibernateUser hb", HibernateUser.class).getResultList();
    }

    @Override
    public List<HibernateUser> findAll(int limit, int offset) {
        return null;
    }

    @Override
    public HibernateUser create(HibernateUser object) {
        return null;
    }

    @Override
    public HibernateUser update(HibernateUser object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> getUserStats() {
        return null;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }
}
