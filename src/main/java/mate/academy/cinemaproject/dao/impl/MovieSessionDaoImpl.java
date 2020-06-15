package mate.academy.cinemaproject.dao.impl;

import java.time.LocalDate;
import java.util.List;
import mate.academy.cinemaproject.dao.MovieSessionDao;
import mate.academy.cinemaproject.exeption.DataProcessingException;
import mate.academy.cinemaproject.model.MovieSession;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger LOGGER = Logger.getLogger(MovieSessionDaoImpl.class);

    private SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "From MovieSession where movie.id = :movieId"
                            + " and year(showTime) = :year and month(showTime) = :month"
                            + " and day(showTime) = :day");
            query.setParameter("movieId", movieId);
            query.setParameter("year", date.getYear());
            query.setParameter("month", date.getMonthValue());
            query.setParameter("day", date.getDayOfMonth());
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available movieSession", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            LOGGER.info("movieSession " + movieSession.toString() + "insert");
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movieSession entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "From MovieSession where id = :id");
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available movieSession", e);
        }
    }
}
