//package org.example.repository;
//
//import org.example.dao.SingerDao;
//import org.example.entities.Singer;
//import org.hibernate.SessionFactory;
//import org.springframework.stereotype.Repository;
//
//import org.springframework.transaction.annotation.Transactional;;
//
//@Transactional
//@Repository("singerDao")
//public class SingerRepository implements SingerDao {
//
//    private final SessionFactory sessionFactory;
//
//    public SingerRepository(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Iterable<Singer> findAll() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("from Singer").list();
//    }
//
//    @Override
//    public Singer findById(int id) {
//        return null;
//    }
//
//    @Override
//    public boolean save(Singer singer) {
//        return false;
//    }
//
//    @Override
//    public boolean delete(Singer singer) {
//        return false;
//    }
//}
