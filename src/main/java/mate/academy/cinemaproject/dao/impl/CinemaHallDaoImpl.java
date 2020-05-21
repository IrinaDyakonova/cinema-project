package mate.academy.cinemaproject.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mate.academy.cinemaproject.dao.CinemaHallDao;
import mate.academy.cinemaproject.exeption.DataProcessingException;
import mate.academy.cinemaproject.lib.Dao;
import mate.academy.cinemaproject.model.CinemaHall;
import mate.academy.cinemaproject.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long cinemaHallId = (Long) session.save(cinemaHall);
            transaction.commit();
            cinemaHall.setId(cinemaHallId);
            LOGGER.info("cinemaHall insert");
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert cinemaHall entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all CinemaHall ", e);
        }
    }

}
