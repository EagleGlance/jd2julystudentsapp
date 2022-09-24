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

    private final SessionFactory sessionFactory;

    //private final EntityManagerFactory entityManagerFactory;

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
//        final String query = "select max(hb.weight) from HibernateUser hb " +
//                "inner join HibernateShopOrder so on so.id = hb.id " +
//                "left join HibernateMedicalInfo mi on mi.id = hb.id " +
//                " where so.sum > 10 and so.sum < 800 ";

        final String query = "select hb from HibernateUser hb " +
                " inner join HibernateMedicalInfo mi on mi.id = hb.id  " +
                " where hb.weight > (select avg(h.id) from HibernateUser h) and " +
                " mi.bloodType = 2 " +
                " " ;

        //final String query = "select * from carshop.users";

        try (Session session = sessionFactory.openSession()) {
            //return session.createNativeQuery(query, HibernateUser.class).getResultList(); - native query run possibility
            return session.createQuery(query, HibernateUser.class).getResultList();
        }

//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        return entityManager.createQuery(query, HibernateUser.class).getResultList();
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
    public Object getUserStats() {

       final String query = "select carshop.get_users_stats_average_weight(false)";

        try (Session session = sessionFactory.openSession()) {
            //return session.createNativeQuery(query, HibernateUser.class).getResultList(); - native query run possibility
            return session.createNativeQuery(query).getSingleResult();
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }
}
