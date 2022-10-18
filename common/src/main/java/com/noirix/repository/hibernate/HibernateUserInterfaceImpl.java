package com.noirix.repository.hibernate;

import com.noirix.domain.SearchCriteria;
import com.noirix.domain.User;
import com.noirix.domain.hibernate.HibernateUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class HibernateUserInterfaceImpl implements HibernateUserInterface {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

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
////        final String query = "select max(hb.weight) from HibernateUser hb " +
////                "inner join HibernateShopOrder so on so.id = hb.id " +
////                "left join HibernateMedicalInfo mi on mi.id = hb.id " +
////                " where so.sum > 10 and so.sum < 800 ";
//
//        final String query = "select hb from HibernateUser hb " +
//                " inner join HibernateMedicalInfo mi on mi.id = hb.id  " +
//                " where hb.weight > (select avg(h.id) from HibernateUser h) and " +
//                " mi.bloodType = 2 " +
//                " ";
//
//        //final String query = "select * from carshop.users";
//
////        try (Session session = sessionFactory.openSession()) {
////            //return session.createNativeQuery(query, HibernateUser.class).getResultList(); - native query run possibility
////            return session.createQuery(query, HibernateUser.class).getResultList();
////        }
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        return entityManager.createQuery(query, HibernateUser.class).getResultList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<HibernateUser> query = entityManager.createNamedQuery("m_users_multiple_ids_search", HibernateUser.class);
        query.setParameter("userIds", 4L);

        return query.getResultList();
    }

    @Override
    @Transactional
    public List<HibernateUser> findAll(int limit, int offset) {

        /*Cache level 2*/
        final String query = "select u from HibernateUser u";

        List<HibernateUser> users;

        Session session = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();

        System.out.println("First session queries");

        Transaction transaction = session.beginTransaction();
        users = session.createQuery(query, HibernateUser.class).getResultList();
        users = session.createQuery(query, HibernateUser.class).getResultList();
        transaction.commit();
        session.close();

        System.out.println("Second session queries");

        Transaction transaction1 = session2.beginTransaction();
        users = session2.createQuery(query, HibernateUser.class).getResultList();
        users = session2.createQuery(query, HibernateUser.class).getResultList();
        transaction1.commit();
        session2.close();

        return users;
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

//       final String query = "select carshop.get_users_stats_average_weight(false)";
//
//        try (Session session = sessionFactory.openSession()) {
//            //return session.createNativeQuery(query, HibernateUser.class).getResultList(); - native query run possibility
//            return session.createNativeQuery(query).getSingleResult();
//        }
        return null;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Object criteriaAPITest(SearchCriteria criteria) {
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        //1. Get Builder for Criteria object
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<HibernateUser> query = cb.createQuery(HibernateUser.class); //here select, where, orderBy, having
//        Root<HibernateUser> root = query.from(HibernateUser.class); //here params  select * from m_users -> mapping
//
//        /*type of future params in prepared statement*/
//        ParameterExpression<String> param = cb.parameter(HibernateUser_.userName.getJavaType());
//        ParameterExpression<Long> userSearchParam = cb.parameter(HibernateUser_.id.getJavaType());
//
//        /*Provide access to fields in class that mapped to columns*/
//        Expression<Long> id = root.get(HibernateUser_.id);
//        Expression<String> name = root.get(HibernateUser_.userName);
//        Expression<String> surname = root.get(HibernateUser_.surname);
//
//        /*SQL Query customizing*/
//        query
//                .select(root)
//                .distinct(true)
//                .where(
//                        cb.or(
//                                cb.like(name, param),  //userName like param
//                                cb.like(surname, param)  //userSurname like param
//                        ),
//                        cb.and(
//                                cb.gt(id, userSearchParam), //>0
//                                cb.not(id.in(40L, 50L)) //in (40,50)
//                        )
////                        ,
////                        cb.between(
////                                root.get(TestUser_.birthDate),
////                                new Timestamp(new Date().getTime()),
////                                new Timestamp(new Date().getTime())
////                        )
//                )
//                .orderBy(cb.asc(root.get(HibernateUser_.id)));
//
//        TypedQuery<HibernateUser> resultQuery = entityManager.createQuery(query); //prepared statement on hql
//        resultQuery.setParameter(param, StringUtils.join("%", criteria.getUserName(), "%"));
//        resultQuery.setParameter(userSearchParam, criteria.getLowerBoundUserId());
//        return resultQuery.getResultList();

        return null;
    }
}
