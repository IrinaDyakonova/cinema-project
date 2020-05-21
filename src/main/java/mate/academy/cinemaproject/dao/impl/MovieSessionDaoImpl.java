package mate.academy.cinemaproject.dao.impl;

import java.time.LocalDate;
import java.util.List;
import mate.academy.cinemaproject.dao.MovieSessionDao;
import mate.academy.cinemaproject.exeption.DataProcessingException;
import mate.academy.cinemaproject.lib.Dao;
import mate.academy.cinemaproject.model.MovieSession;
import mate.academy.cinemaproject.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
            throw new DataProcessingException("Can't get available sessions", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(movieSessionId);
            LOGGER.info("movieSession insert");
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert movieSession entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
