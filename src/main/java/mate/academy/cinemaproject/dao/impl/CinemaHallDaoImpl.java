package mate.academy.cinemaproject.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mate.academy.cinemaproject.dao.CinemaHallDao;
import mate.academy.cinemaproject.exeption.DataProcessingException;
import mate.academy.cinemaproject.model.CinemaHall;
import mate.academy.cinemaproject.model.Movie;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger LOGGER = Logger.getLogger(CinemaHallDaoImpl.class);

    private SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            LOGGER.info("cinemaHall " + cinemaHall.toString() + " insert");
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert cinemaHall entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all CinemaHall ", e);
        }
    }

    @Override
    public CinemaHall findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> query = session.createQuery(
                    "From CinemaHall where id = :id");
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available cinemaHall", e);
        }
    }

}
